<template>
  <div>
    <b-container class="my-profile">
      <b-row align-v="center" align-h="center">
          <h1>{{user.name}}님 환영합니다!!</h1>
      </b-row>
      <b-row align-v="center" align-h="center" style="margin: 20px;">
        <b-button class="ml-2" variant="danger" @click="moveUpdateUser()">정보 수정</b-button>
        <b-button v-if="authority === 2" class="mybutton2 ml-2" @click="sellerpost()">업체 등록</b-button>
        <b-button v-else-if="authority === 3" class="mybutton2 ml-2" @click="sellerread()">업체 정보</b-button>
      </b-row>
      <b-row align-v="center" align-h="center">
      <div class="tab-content">
          <b-tabs content-class="mt-3" fill>
              <b-tab title="예약/대여 현황" active> 
                <carousel-3d v-if="leisures.length > 0" :width="200" :height="400" controls-visible :perspective="0" :space="300" >
                    <slide v-for="(leisure, i) in leisures" :key="i" :index="i">
                    <div class="post-card">
                      <h5 class="shop-name">{{leisure.shopName}}</h5>
                      <h6 style="text-align: right; padding-right: 5px;">{{leisure.date.slice(0,10)}}</h6>
                      <div style="text-align: center; margin-top: 5px; font-weight: 700;">구매한 상품</div>
                      <div class="post-info">
                          <div class="post-text">
                            <div v-for="(service, k) in leisure.products" :key="k" :index="k" style="font-size: 1rem;">
                              {{service.name}}
                            </div>
                          </div>
                      </div>
                      <div class="card-button">
                        <b-button class="button5" v-if="leisure.reviewed===0" @click="writereview(leisure.id)">후기 작성</b-button>
                        <b-button class="button6" v-if="leisure.reviewed===1" @click="updatereview(leisure.id)">후기 수정</b-button>
                      </div>
                    </div>
                    </slide>
                </carousel-3d>
                <div v-else class="no-search">
                    <h3>검색결과 없음</h3>
                </div>
              </b-tab>
              <b-tab title="내가 쓴 레저 팁">
                <div class="my-tip">
                <table class="table table-bordered">
                <thead>
                    <tr style="background-color: #bbbfca;">
                      <th style="width:70%;">제목</th>
                      <th style="width:15%;">날짜</th>
                      <th style="width:15%;">조회수</th>
                    </tr>
                </thead>
                <tbody>
                    <tr
                    style="background-color: #e8e8e8;"
                    v-for="(notice,id) in notices"
                    :key="id"
                    >
                    <td @click="moveDetail(notice.id)"><span class="tip-title">{{notice.title.slice(0, 8)}}</span></td>
                    <td>{{notice.date.slice(0, 10)}}</td>
                    <td>{{notice.hits}}</td>
                    </tr>
                </tbody>
                </table>
                </div>
              </b-tab>
          </b-tabs>
      </div>
      </b-row>
      <b-row align-v="center" align-h="end" style="padding-right: 10%;">
        <span v-b-modal.modal-prevent-closing>탈퇴하기</span>
      </b-row>
    </b-container>
    <b-modal id="modal-prevent-closing" modal-class="mymodal" hide-footer>
      <template v-slot:modal-title>
        탈퇴 확인창
      </template>
      <div class="d-block text-center">
        <h3>탈퇴하시려면</h3>
        <h3>'탈퇴하겠습니다'</h3>
        <h3>라고 입력해주세요</h3>
        <b-form @submit.prevent="deleteAccount" class="my-3">
          <b-form-input v-model="outtext" placeholder="탈퇴하겠습니다"></b-form-input>
          <b-button class="my-3" block type="submit" variant="danger">탈퇴합니다</b-button>
        </b-form>
      </div>
      <b-button class="my-3" block @click="$bvModal.hide('modal-prevent-closing')">취소</b-button>
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios'

const API_URL = process.env.VUE_APP_SERVER_URL;

import empty from '../assets/empty.png';
import { Carousel3d, Slide } from 'vue-carousel-3d';

export default {
  name: "MyProfile",
  data(){
      return{
          notices: [],
          reviews: [],
          user: {},
          leisures: [],
          authority: 0,

          outtext: "",
          empty,
      }
  },
  created(){
    window.scrollTo(0,0);
      const uid = this.$cookies.get("yol_uid");

      axios({
        method:"GET",
        url:`${API_URL}/user/findById/${uid}`,
        })
        .then(({ data }) => {
            this.user = data.data
          })
        .catch(err => {
            console.log(err)
            alert("정보를 받아올때 에러가 발생했습니다.");
        });

        axios({
          method: "GET",
          url: `${API_URL}/reservations`,
          params: {
            id: uid,
          }
          })
          .then(({ data }) => {
            this.leisures = data.data;
          })
          .catch((err) => {
            alert("정보를 받아올때 에러가 발생했습니다.");
            console.log(err);
        });



      axios({
      method: "GET",
      url: `${API_URL}/user/authority/${uid}`,
      params: {
        page: 0,
      }
      })
      .then(({ data }) => {
        this.authority = data.data;
      })
      .catch((err) => {
        alert("정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });

      axios({
      method: "GET",
      url: `${API_URL}/tip/user/${uid}`,
      params: {
        page: 0,
      }
      })
      .then(({ data }) => {
        this.notices = data.data;
      })
      .catch((err) => {
        alert("정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });
  },
  components: {
    Carousel3d,
    Slide
  },
  methods: {
    reviewlist(id){
      this.$router.push({ path: "/reviewlist/" + id });
    },
    moveUpdateUser(){
      this.$router.push({ path: "/updateuser" });
    },

    moveDetail(id) {
      axios.get(`${API_URL}/tip/increase/`+ id)
      .catch((err) =>{
        console.log(err)
      })
      this.$router.push({ path: "/tipdetail/" + id });
    },
    writereview(id){
      this.$router.push({ path: "/writereview/" + id});
    },
    updatereview(id){
      this.$router.push({ path: "/updatereview/" + id});
    },
    sellerpost(){
      this.$router.push({ path: "/sellerpost" });
    },
    sellerread(){
      this.$router.push({ path: "/sellerread" });
    },
  }

}
</script>

<style scoped>
  .my-profile {
    padding-top: 100px;
    background-color: #f4f4f2;
    padding-bottom: 5%;
    margin-bottom: -30px;
    margin: auto;
  }
  .tab-content{
    padding-top: 50px;
    width: 80%;
  }
  .no-search{
      justify-content: center;
  }
  .total-result {
    padding-top: 100px;
    background-color: #f4f4f2;
  }
  .carousel-3d-slide {
    background-color: #f4f4f2;
    border-style: none;
  }
  .post-card {
    background-color: #E8E8E8;
    margin: 40px 0px;
    max-width: 300px;
    height: 320px;
    border-radius: 5px;
    position: relative;
    font-family: 'roboto', Sans-Serif;
    text-align: center;
    overflow: hidden;
  }
  .post-info{
    display: flex;
    align-items: flex-start;
    margin-top: 25px;
    margin-left: 5px;
    height: 140px;
  }
  .post-tip{
    display: flex;
    align-items: center;
    margin-top: 40%;
    margin-left: 5px;
  }
  .post-text {
      text-align: left;
      margin-left: 15px;
      margin-right: 15px;
  }
  .post-desc {
      margin: 4px 0;
      font-size: 12px;
  }
  .post-add {
    width: 100%;
    text-align: right;
  }
  .more {
    width: 100%;
    text-align: right;
  }
  .no-search {
    width: 100%;
    height: 400px;
  }
  .no-search img {
    width: 100px;
    height: 100px;
    margin-top: 7%;
    margin-bottom: 7%;
  }
  .shop-name{
    background-color: #BBBFCA;
    padding: 10px;
    font-weight: 700;
  }
  .card-button{
    width: 100px;
    margin-left: 25%;
  }
  .button5{
    background-color: #0c353a;
  }
 .button6{
    background-color: #084481;
  }
  .grow { 
  transition: all .2s ease-in-out; 
  }
  .grow:hover { 
  transform: scale(1.05); 
  }
  .mybutton2 {
    margin: 20px;
    width: 100px;
    background-color: #084481;
    border: none;
  }
  .my-tip {
    padding-left: 2%;
    padding-right: 2%;
    overflow: auto;
    height: 422px;
  }
  .tip-title {
    cursor: pointer;
  }
  .tip-title:hover {
    text-decoration: underline;
  }
</style>