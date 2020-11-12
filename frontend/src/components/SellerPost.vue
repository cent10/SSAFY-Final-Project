<template>
  <div class="seller-post">
    <h1 class="mt-2 mb-3">업체 등록</h1>
    <div class="d-none d-md-block">
      <div class="form-group">
        <div class="d-flex justify-content-between mb-3">
          <div style="width: 45%;">
            <input type="text" class="form-control" placeholder="* 이름" v-model="name" />
            <input type="text" class="form-control" placeholder="* 업체번호" v-model="admin" />
            <input type="text" class="form-control" placeholder="* 전화번호" v-model="phone" />
            <input type="text" class="form-control" placeholder="* 주소" v-model="address" />

            <!-- <div class="d-flex flex-column">
            img업로드 : 
              <input type="file" class="d-block my-3" @change="onFileChanged1" />
              <b-button id="upload" class="mybutton1" style="width: 70px;" @click="onUpload">Upload!</b-button>
            </div>
            <div class="d-flex flex-column">
            imgDesc업로드 :
              <input type="file" class="d-block my-3" @change="onFileChanged2" />
              <b-button id="upload" class="mybutton1" style="width: 70px;" @click="onUpload">Upload!</b-button>
            </div> -->
          </div>
        </div>

        <b-col>
            <h5>지역</h5>
            <b-form-select v-model="selectedRegion">
              <b-form-select-option value="null">선택하세요</b-form-select-option>
              <b-form-select-option v-for="(region,id) in regions" :key="id" :value="region">{{region}}</b-form-select-option>
            </b-form-select>
          </b-col>
          <b-col>
            <h5>카테고리</h5>
            <b-form-select v-model="selectedCategory">
              <b-form-select-option value="null">선택하세요</b-form-select-option>
              <b-form-select-option v-for="(onecategory,id) in categorys" :key="id" :value="onecategory.category">{{onecategory.category}}</b-form-select-option>
            </b-form-select>
        </b-col>

        <select v-model="classification">
            <option value="false">서비스</option>
            <option value="true">장비</option>
        </select>

        <input type="text" class="form-control my-2" placeholder="사업자등록번호" v-model="number" />
        <input type="text" class="form-control my-2" placeholder="설명" v-model="description" />
       

       
      </div>
      <b-button block class="mybutton2 mb-3" @click="submit()">등록</b-button>
      <b-button block class="mb-4" @click="moveNotice()">취소</b-button>
    </div>
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
      admin: null,
      phone: "",
      address: "",
      classification: false,
      number: "",
      description: "",
      categorys: [],

      
      selectedRegion: null,
      selectedCategory: null,


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
          admin: this.admin,
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
        .then(({ data }) => {
          console.log(data);
          
          this.$router.push({ path: `/sellerread` }); 
          
        })
        .catch((err) => {
          console.log(err);
          alert("입력오류가 발생했습니다. \n 다시한번 확인해주세요!");
        });
    },
    moveNotice() {
      this.$router.push({ path: "/articles_find/" });
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
.seller-post{
    padding-top: 100px;
    background-color: #F2F2F5;
}
</style>
