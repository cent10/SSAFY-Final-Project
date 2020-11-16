<template>
  <div class="equip-detail">
    <b-container v-if="!isPurchasing">
      <b-row align-h="center" align-v="center" style="padding-bottom: 5%">
        <b-col align-self="center">
          <div class="equip-logo">
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
          <div :class="{ 'equip-desc' : !isShowDesc, 'equip-desc-show' : isShowDesc }">
            <b-img :src="shop.imgDesc" class="desc" />
          </div>
          <div style="padding-top: 3%; padding-bottom: 5%">
            <b-button v-if="!isShowDesc" @click="toggleShowDesc" style="background-color: #495464;">자세한 설명 보기</b-button>
            <b-button v-else @click="toggleShowDesc" style="background-color: #495464;">자세한 설명 숨기기</b-button>
          </div>
        </b-col>
      </b-row>
      <b-row v-if="reviews !== null && reviews.length > 0" style="padding-top: 3%; padding-bottom: 5%">
        <b-col>
         <h5>후기리스트</h5>
         <b-list-group style="max-height: 500px; overflow: auto;">
            <b-list-group-item class="review-item" v-for="(review, i) in reviews" :key="i">
                <b-row align-h="start" class="review-item-user">
                  <b-col cols=2>
                  </b-col>
                  <b-col cols=6>
                    <b-row style="margin-left: 0; width:100%;">
                        <StarRating v-model="review.rate" :max-stars="5"/>
                    </b-row>
                    <b-row style="margin-left: 0; width:100%;">
                      <b-button variant="none" @click="showDetail(i)" class="review-content">{{review.summaryCont}}</b-button>
                    </b-row>
                  </b-col>
                  <b-col cols=4>
                    {{review.date}}
                  </b-col>
                </b-row>
                <b-row v-if="review.reply !== undefined" class="review-item-seller">
                  <b-col cols=2 style="text-align: right;">
                    <b-icon icon="arrow-return-right"/>
                  </b-col >
                  <b-col cols=6 style="word-break:break-all;">
                    {{review.reply.content}}
                  </b-col>
                  <b-col cols=4>
                    {{review.reply.date.slice(0,10)}}
                  </b-col>
                </b-row>
            </b-list-group-item>
          </b-list-group>
        </b-col>
      </b-row>
      <b-row v-else align-h="center" style="padding-top: 3%; padding-bottom: 5%">
        <b-col>
        <h5>후기리스트</h5>
        <div style="padding:20px;">등록된 후기가 없습니다.</div>
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
                  <td style="text-align: left; padding-left: 5%;">
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
                      <b-btn variant="info btn-sm ml-1 mt-1 mb-1" @click="upBuyNum(i)"><span class="fas fa-plus"/></b-btn>
                      <b-btn variant="warning btn-sm mr-1 mt-1 mb-1" @click="downBuyNum(i)"><span class="fas fa-minus"/></b-btn>
                    </div>
                  </td>
                  <td>
                    일회구매제한: {{product.num}}
                  </td>
                </tr>
              </table>
            </b-list-group-item>
          </b-list-group>
        </b-col>
      </b-row>
      <b-row v-else align-h="center">
        <b-col>
        <h5>상품리스트</h5>
        <div style="padding:20px;">등록된 상품이 없습니다.</div>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-button @click="purchase" style="margin-right: 0.5%; background-color: #084481;" v-if="this.$cookies.isKey('yol_token')">결제하기</b-button>
          <b-button @click="moveSearchPage" style="margin-left: 0.5%; background-color: #084481;">목록으로</b-button>
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
  name: "EquipDetail",
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
      reviews: [],
      products: [],
      isShowDesc: false,
      checkedProducts: [],
      isPurchasing: false,
      purchasingList: [],
      modalRate: 0,
      modalContent: "",
    }
  },
  components: {
    Purchase,
    StarRating,
  },
  created() {
    window.scrollTo(0,0);
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
      this.shop.img = "http://k3a210.p.ssafy.io/img/activityx_shop_img/" + shop.img;
      this.shop.imgDesc = "http://k3a210.p.ssafy.io/img/activityx_shop_imgDesc/" + shop.imgDesc;
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

    
    axios({
        method: "GET",
        url: `${API_URL}/reviews/list/` + this.$route.params.id,
      }).then(({data})=>{
        this.reviews = data.data;

        this.reviews.map((r) => {
          r.date = r.date.slice(0,10);

          if(r.content.length > 18){
            r.summaryCont = r.content.slice(0, 15) + ".....";
          } else {
            r.summaryCont = r.content;
          }

          axios({
            method: "GET",
            url: `${API_URL}/reviews/${r.id}/replies/0`,
          })
          .then(({data}) => {
            if(data.status)
              r.reply = data.data;
            this.$forceUpdate();
          })
          .catch((err) => {
            console.log(err);
            alert("후기 답글 정보 받아올때 에러가 발생했습니다.");
          });

        });

      }).catch((err) => {
          console.log(err);
          alert("후기 정보를 받아올때 에러가 발생했습니다.");
      });

  },
  methods: {
    moveSearchPage(){
      this.$router.push({name: "EquipList"});
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
    showDetail(i) {
      this.modalRate = this.reviews[i].rate;
      this.modalContent = this.reviews[i].content;
      this.$bvModal.show('review-detail');
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
.equip-detail {
  margin: auto;
  padding: 110px;
  width: 80%;
  background-color: #f4f4f2;
}
.logo {
  width: 100%;
  height: 100%;
}
.equip-logo {
  width: 200px;
  height: 200px;
  background-color: gray;
  margin: 2% auto;
}
.equip-desc {
  max-height: 400px;
  border: 2px solid black;
  background-color: gray;
  overflow: hidden;
}
.equip-desc-show {
  border: 2px solid black;
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
.review-item {
  height: 200px;
  padding: 0;
}
.review-item-user {
  overflow: auto;
  width: 100%;
  margin: 0;
  height: 80px;
  padding: 10px;
  text-align: left;
}
.review-item-seller {
  overflow: auto;
  width: 100%;
  margin: 0;
  height: 120px;
  padding: 10px;
  text-align: left;
  background-color: #e8e8e8;
}
.review-content {
  padding: 0;
  transition: all 0.5s;
}
.review-content:hover {
  cursor: pointer;
  text-decoration: underline;
}
</style>
