<template>
  <div class="leisure-detail">
    <b-container v-if="!isPurchasing">
      <b-row align-h="center" align-v="center" style="padding-bottom: 5%">
        <b-col align-self="center">
          <div class="leisure-logo">
            <b-img :src="shop.img" class="logo" />
          </div>
        </b-col>
        <b-col>
          <table>
            <tr>
              <td>
              카테고리
              </td>
              <td>
                {{shop.category}}
              </td>
            </tr>
            <tr>
              <td>
              업체명
              </td>
              <td>
                {{shop.name}}
              </td>
            </tr>
            <tr>
              <td>
              주소
              </td>
              <td>
                {{shop.address}}
              </td>
            </tr>
            <tr>
              <td>
              연락처
              </td>
              <td>
                {{shop.phone}}
              </td>
            </tr>
            <tr>
              <td>
              사업자번호
              </td>
              <td>
                {{shop.number}}
              </td>
            </tr>
            <tr>
              <td>
                평점
              </td>
              <td>
                <StarRating v-model="shop.rate" :max-stars="5"/>
              </td>
            </tr>
          </table>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <div :class="{ 'leisure-desc' : !isShowDesc, 'leisure-desc-show' : isShowDesc }">
            <!-- <b-img :src="shop.imgDesc" class="desc" /> -->
            <b-img src="http://www.saraminimage.co.kr/recruit/bbs_recruit2/hj_n_190617.png" class="desc" />
          </div>
          <div style="padding-top: 3%; padding-bottom: 5%">
            <b-button v-if="!isShowDesc" @click="toggleShowDesc">자세한 설명 보기</b-button>
            <b-button v-else @click="toggleShowDesc">자세한 설명 숨기기</b-button>
          </div>
        </b-col>
      </b-row>
      <b-row v-if="products !== null && products.length > 0">
        <b-col>
          <h5>상품리스트</h5>
          <b-list-group class="yol-list-group">
            <b-list-group-item v-for="(product, i) in products" :key="i">
              <table class="product-list">
                <colgroup>
                  <col width="30%">
                  <col width="30%">
                  <col width="20%">
                  <col width="20%">
                </colgroup>
                <tr>
                  <td>
                    <b-form-checkbox :value="i" v-model="checkedProducts">
                      {{product.name}}
                    </b-form-checkbox>
                  </td>
                  <td>
                    {{product.price}}원
                  </td>
                  <td>
                    <div class="product-count">
                      <b-form-input type="number" v-model="product.buyNum" disabled />개
                      <b-btn variant="info btn-sm ml-1 mt-1 mb-1" @click="upBuyNum(i)">+</b-btn>
                      <b-btn variant="warning btn-sm mr-1 mt-1 mb-1" @click="downBuyNum(i)">-</b-btn>
                    </div>
                  </td>
                  <td>
                    일일판매수량: {{product.num}}
                  </td>
                </tr>
              </table>
            </b-list-group-item>
          </b-list-group>
        </b-col>
      </b-row>
      <b-row v-else>
        등록된 상품이 없습니다.
      </b-row>
      <b-row>
        <b-col>
          <b-button @click="purchase" style="margin-right: 0.5%;" v-if="this.$cookies.isKey('yol_token')">결제하기</b-button>
          <b-button @click="moveSearchPage" style="margin-left: 0.5%;">목록으로</b-button>
        </b-col>
      </b-row>
    </b-container>
    <Purchase v-else :pl="purchasingList" v-model="isPurchasing"/>
  </div>
</template>

<script>
const API_URL = process.env.VUE_APP_SERVER_URL;

import Purchase from "./Purchase.vue";
import StarRating from "./StarRating.vue";

import axios from 'axios';

export default {
  name: "LeisureDetail",
  data: function () {
    return {
      shop: {
        id: 0,
        name: "",
        address: "",
        phone: "",
        number: 0,
        description: "",
        imgDesc: "",
        img: "",
        category: "",
        rate: 0,
      },
      products: [],
      isShowDesc: false,
      checkedProducts: [],
      isPurchasing: false,
      purchasingList: [],
    }
  },
  components: {
    Purchase,
    StarRating,
  },
  created() {
    axios({
      method: "GET",
      url: `${API_URL}/shops/` + this.$route.params.id,
    }).then(({data})=>{
      const shop = data.data.shop;
      this.shop.category = data.data.category;
      this.shop.rate = data.data.rate;
      this.shop.id = shop.id;
      this.shop.name = shop.name;
      this.shop.address = shop.address;
      this.shop.phone = shop.phone;
      this.shop.number = shop.number;
      this.shop.description = shop.description;
      this.shop.imgDesc = shop.imgDesc;
      this.shop.img = shop.img;
    }).catch((err) => {
        console.log(err);
        alert("업체 정보를 받아올때 에러가 발생했습니다.");
    });

    axios({
      method: "GET",
      url: `${API_URL}/shops/` + this.$route.params.id + `/products`,
    }).then(({data})=>{
      this.products = data.data;
      this.products.map((p) => {p.buyNum = 0;});
    }).catch((err) => {
        console.log(err);
        alert("상품 정보를 받아올때 에러가 발생했습니다.");
    });
  },
  methods: {
    moveSearchPage(){
      this.$router.push({name: "LeisureList"});
    },
    upBuyNum(i){
      const tmp = Object.assign({}, this.products[i]);
      tmp.buyNum++;
      if(tmp.buyNum > tmp.num) return;
      this.$set(this.products, i, tmp);
    },
    downBuyNum(i){
      const tmp = Object.assign({}, this.products[i]);
      tmp.buyNum--;
      if(tmp.buyNum < 0) return;
      this.$set(this.products, i, tmp);
    },
    toggleShowDesc(){
      this.isShowDesc = !this.isShowDesc;
      window.scrollTo(0, 0);
    },
    purchase(){
      const list = [];
      const purchasingIdx = this.checkedProducts;
      const removeIdx = [];

      if(purchasingIdx.length === 0) {
        alert("상품을 선택해주세요.");
      } else {
        purchasingIdx.map((pi) => {
          if(this.products[pi].buyNum === 0){
            removeIdx.push(pi);
          } else {
            list.push(this.products[pi]);
          }
        });

        removeIdx.map((ri) => {
          const idx = purchasingIdx.indexOf(ri);
          if (idx > -1) purchasingIdx.splice(idx, 1);
        });

        if(list.length === 0)
          alert("상품 개수를 확인해주세요.");
        else{
          this.purchasingList = list;
          this.isPurchasing = true;
        }
      }
    }
  },
};
</script>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
li {
  list-style-type: none;
}
.product-count {
  display: flex;
}
.leisure-detail {
  padding-top: 110px;
  width: 80%;
  margin: auto;
}
.logo {
  width: 100%;
  height: 100%;
}
.leisure-logo {
  width: 200px;
  height: 200px;
  background-color: gray;
  margin: 2% auto;
}
.leisure-desc {
  max-height: 400px;
  background-color: gray;
  overflow: hidden;
}
.leisure-desc-show {
  background-color: gray;
}
.desc {
  width: 100%;
}
.product-list {
  text-align: center;
}
.yol-list-group{
  margin-bottom: 3%;
}
</style>
