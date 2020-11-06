<template>
  <div class="leisure-detail">
    <b-container>
      <b-row align-h="center">
        <b-col>
          <div class="leisure-logo"></div>
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
          </table>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <div class="leisure-desc"></div>
        </b-col>
      </b-row>
      <b-row v-if="products !== null && products.length > 0">
        <b-col>
          <h5>상품리스트</h5>
          <b-list-group>
            <b-list-group-item v-for="(product, i) in products" :key="i">
              <table>
                <tr>
                  <td>
                    <b-form-checkbox/>
                  </td>
                  <td>
                    {{product.name}}
                  </td>
                  <td>
                    설명: {{product.description}}
                  </td>
                  <td>
                    <b-form-input type="number"/>개
                    <b-btn variant="info btn-sm ml-1 mt-1 mb-1">+</b-btn>
                    <b-btn variant="warning btn-sm mr-1 mt-1 mb-1">-</b-btn>
                  </td>
                  <td>
                    일일판매수량: {{product.num}}
                  </td>
                </tr>
              </table>
            </b-list-group-item>
          </b-list-group>
          <b-button>결제하기</b-button>
          <b-button @click="moveSearchPage">목록으로</b-button>
        </b-col>
      </b-row>
      <b-row v-else>
        등록된 상품이 없습니다.
      </b-row>
    </b-container>
  </div>
</template>

<script>
const API_URL = process.env.VUE_APP_SERVER_URL;

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
        img: "",
        category: "",
        rate: 0,
      },
      products: [],
    }
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
    }).catch((err) => {
        console.log(err);
        alert("상품 정보를 받아올때 에러가 발생했습니다.");
    });
  },
  methods: {
    moveSearchPage(){
      this.$router.push({name: "Home"}); // 나중에 레저검색 페이지로 이동하도록 변경
    }
  }
};
</script>

<style scoped>
li {
  list-style-type: none;
}
.leisure-detail {
  padding-top: 100px;
}
.leisure-logo {
  width: 100px;
  height: 100px;
  background-color: black;
}
.leisure-desc {
  height: 800px;
  background-color: gray;
}
</style>
