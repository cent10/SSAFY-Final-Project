<template>
  <div class="my-profile">
    <div>
        <h1>{{user.name}}님 환영합니다!!</h1>
    </div>
    <div>

    </div>
    <h1>
    <div>
      <b-button class="mybutton2 ml-2">정보 수정</b-button>
    </div>
    </h1>
    <div>
      <b-button v-b-modal.modal-prevent-closing class="mybutton4 ml-2">탈퇴하기</b-button>
    </div>
    <div class="tab-content">
        <b-tabs content-class="mt-3" fill>
            <b-tab title="예약/대여 현황" active> 
                    <carousel-3d v-if="leisures.length > 0" :width="200" :height="400" controls-visible :perspective="0" :space="300" >
                        <slide v-for="(leisure, i) in leisures" :key="i" :index="i">
                        <div class="post-card">
                        
                        <div class="post-info">
                            <div class="post-text">
                            <h5>{{leisure.shopName}}</h5>
                            <!-- <p class="post-desc">{{leisure.description}}</p> -->
                            <b-button v-if="leisure.reviewed===0" @click="writereview(leisure.id)">후기 작성</b-button>
                            <b-button v-if="leisure.reviewed===1" @click="updatereview(leisure.id)">후기 수정</b-button>
                            <h6>{{leisure.date.slice(0,10)}}</h6>
                            </div>
                            <div v-for="(service, k) in leisure.products" :key="k" :index="k">
                              <h5>{{service.name}}</h5>
                            </div>
                        </div>
                        </div>
                        </slide>
                    </carousel-3d>
                    <div v-else class="no-search">
                        <h3>검색결과 없음</h3>
                    </div>
            </b-tab>
            <b-tab title="내가 쓴 레저 팁">
                    <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                        <th style="width:20%;">제목</th>
                        <th style="width:20%;">내용</th>
                        <th style="width:15%;">날짜</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr
                        v-for="(notice,id) in notices"
                        :key="id"
                        @click="moveDetail(notice.id)"
                        >
                        <td>{{notice.title.slice(0, 8)}}</td>
                        <td>{{notice.content.slice(0, 7)}}</td>
                        <td>{{notice.date.slice(0,10)}}</td>
                        </tr>
                    </tbody>
                    </table>
            </b-tab>
        </b-tabs>
    </div>
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
          leisures: {},

          outtext: "",
          empty,
          // leisures: [
          //   {
          //   name: '업체명1',
          //   description: '설명설명설명설명설명설명1',
          //   // logo: ''
          //   },
          //   {
          //   name: '업체명2',
          //   description: '설명설명설명설명설명설명2',
          //   // logo: ''
          //   },
          //   {
          //   name: '업체명2',
          //   description: '설명설명설명설명설명설명2',
          //   // logo: ''
          //   },
          // ],
          // equips: [
          //   {
          //   name: '업체1',
          //   description: '설명설명설명설명설명설명1',
          //   // logo: ''
          //   },
          //   {
          //   name: '업체2',
          //   description: '설명설명설명설명설명설명2',
          //   // logo: ''
          //   },
          //   {
          //   name: '업체3',
          //   description: '설명설명설명설명설명설명2',
          //   // logo: ''
          //   },
          // ]
      }
  },
  created(){
      const uid = this.$cookies.get("uid");
      console.log(uid);

      axios({
        method:"GET",
        url:`${API_URL}/user/findById/${uid}`,
        })
        .then(({ data }) => {
            console.log(data.data);
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
            console.log(data)
            this.leisures = data.data;
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
        console.log(data)
        this.notices = data.data;
        //   console.log(this.data)
        //   this.comments = notices.comments;
        //   console.log(comments)
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
  }

}
</script>

<style scoped>
  .element.style {
    height: 402px;
  }
  .my-profile {
    padding-top: 100px;
    background-color: #F2F2F5;
  }
  .tab-content{
    padding-top: 50px;
  }
  .no-search{
      justify-content: center;
  }

  .total-result {
    padding-top: 100px;
    background-color: #F2F2F5;
  }
  .carousel-3d-slide {
    background-color: #F2F2F5;
    border-style: none;
  }
  .post-card {
    background-color: white;
    margin: 40px 0px;
    max-width: 300px;
    height: 320px;
    border-radius: 5px;
    position: relative;
    font-family: 'roboto', Sans-Serif;
    text-align: center;
    overflow: hidden;
  }
  .post-tag {
    position: absolute;
    right: -6px;
    top: 15px; 
    background-color: #FFD95B;
    font-size: 10px;
    padding: 7px;
    letter-spacing: .4px;
    text-transform: uppercase;
    box-shadow: 0px 1px 2px 0px rgba(0,0,0,0.21);
    z-index: 99;
  }
  .circle {
    height: 50%;
    width: 100%;
    background-color: darkgray;
    display: inline-block;
    position: relative;
  } 
  .circle img {
    max-width: 100%;
    max-height: 100%;
  }
  .post-info{
    display: flex;
    align-items: center;
    margin-top: 25px;
    margin-left: 5px;
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






  .grow { 
  transition: all .2s ease-in-out; 
  }
  .grow:hover { 
  transform: scale(1.05); 
  }
  .mybutton2 {
    width: 20%;
    background-color: #084481;
    border: none;
  }
  .mybutton4 {
    width: 20%;
    background-color: #FA1E44;
    border: none;
  }
  ::v-deep .mymodal {
    font-family: 'Cafe24Oneprettynight';
  }
  .popover {
    font-family: 'Cafe24Oneprettynight';
    font-weight: bold;
  }
</style>