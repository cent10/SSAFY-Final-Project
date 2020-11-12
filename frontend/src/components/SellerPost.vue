<template>
  <div class="seller-post">
    <h1 class="mt-2 mb-3">업체 등록</h1>
    <b-container>
      <b-row>
        <b-col class="shop-info-1">
          <input type="text" class="form-control my-2" placeholder="업체명" v-model="name" />
          <input type="text" class="form-control my-2" placeholder="전화번호 ex) 02-1234-5678" v-model="phone" />
          <input type="text" class="form-control my-2" placeholder="주소" v-model="address" />
          <input type="text" class="form-control my-2" placeholder="사업자등록번호" v-model="number" />
          <textarea class="form-control my-2" placeholder="업체간단설명(50자 이내)" v-model="description" style="resize: none; height: 100px"/>
        </b-col>
        <b-col class="shop-info-2">
          <h5>제공 서비스 종류</h5>
          <b-form-select v-model="classification" style="margin-bottom: 10px;">
              <b-form-select-option value="false">레저/액티비티</b-form-select-option>
              <b-form-select-option value="true">장비</b-form-select-option>
          </b-form-select>
          <h5>지역</h5>
          <b-form-select v-model="selectedRegion" style="margin-bottom: 10px;">
            <b-form-select-option v-for="(region,id) in regions" :key="id" :value="region">{{region}}</b-form-select-option>
          </b-form-select>
          <h5>카테고리</h5>
          <b-form-select v-model="selectedCategory" style="margin-bottom: 10px;">
            <b-form-select-option v-for="(onecategory,id) in categorys" :key="id" :value="onecategory.category">{{onecategory.category}}</b-form-select-option>
          </b-form-select>
      </b-col>
      </b-row>
      <b-row align-v="center">
        <b-col>
          <b-button block class="mybutton2 mb-3" @click="submit()" style="width: 50%; margin: auto;">등록</b-button>
        </b-col>
        <b-col>
          <b-button block class="mb-4" @click="moveNotice()" style="width: 50%; margin: auto;">취소</b-button>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";

const API_URL = process.env.VUE_APP_SERVER_URL;


export default {
  name: "SellerPost",
  data() {
    return {
      name: "",
      phone: "",
      address: "",
      classification: false,
      number: "",
      description: "",
      categorys: [],
      
      selectedRegion: "서울",
      selectedCategory: "산악자전거",


      regions: [
        "서울",
        "부산",
        "대구",
        "인천",
        "광주",
        "대전",
        "울산",
        "경기",
        "강원",
        "충북",
        "충남",
        "전북",
        "전남",
        "경북",
        "경남",
        "제주",
        "세종",
      ],

    }
  },
  
  created() {
    axios
      .get(`${API_URL}/category/all`)
      .then((res) => {
        this.categorys = res.data.data;
      })
      .catch((err) => {
        alert("카테고리 정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });


  },

  components: {

  },
  computed: {
    
  },
  methods: {
    submit() {
      axios({
        method: "POST",
        url: `${API_URL}/shops/`,
        data: {
          name: this.name,
          address: this.address,
          phone: this.phone,
          classification: this.classification,
          number: this.number,
          description: this.description,
          region: this.selectedRegion,
          img: "",
          imgDesc: "",
        
        },
        params: {
            categoryName: this.selectedCategory
        }
      })
        .then(() => {
          this.$router.push({ path: `/sellerread` }); 
        })
        .catch((err) => {
          console.log(err);
          alert("입력오류가 발생했습니다. \n 다시한번 확인해주세요!");
        });
    },
    moveNotice() {
      this.$router.push({ path: "/myprofile" });
    },

  },
};
</script>

<style scoped>
.mybutton1 {
  background-color: #084481;
  border: none;
}
.mybutton2 {
  background-color: #fa1e44;
  border: none;
}
.seller-post {
    margin: 100px auto 20px auto;
    padding: 20px;
    width: 60%;
    background-color: #F2F2F5;
}
.shop-info-1 {
  padding: 5%;
}
.shop-info-2 {
  padding: 5%;
}
.image-upload {
  min-height: 300px;
}
</style>
