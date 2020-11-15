<template>
  <div class="notice-detail">
    <h3 style="text-align: left; padding-left: 10%;">공지사항</h3>
      <table class="table table-bordered mt-3" style="width: 80%; margin: auto; font-size: 15px;">
        <thead>
          <tr style="background-color: #bbbfca;">
            <th style="width: 70%; vertical-align: middle;">{{notice.title}}</th>
            <th style="width: 20%;">
              <span v-if="notice === {}">{{notice.date.slice(0,10)}}</span>
            </th>
            <th style="width: 10%;">조회 {{notice.hits}}</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td colspan="4" style="padding: 0;">
              <b-textarea disabled no-resize :value="notice.content" style="height: 400px; background-color: #e8e8e8;"/>
            </td>
          </tr>
        </tbody>
      </table>
    <b-button class="mybutton1" @click="moveNoticeModify()" v-if="this.auth==1" style="margin: 20px;">수정</b-button>

    <b-button class="mybutton2 mx-3" @click="moveNoticeDelete()" v-if="this.auth==1" style="margin: 20px;">삭제</b-button>

    <b-button class="border-0" @click="moveNotice()" style="margin: 20px;">목록으로</b-button>

    
  </div>
</template>

<script>

const API_URL = process.env.VUE_APP_SERVER_URL;

// import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "NoticeDetail",
  data() {
    return {
    
      notice: {},
      auth: "",
      datas: {},
      id: "",
      comment: "",
      title: "",
      // uid: this.$cookies.get('yol_uid'),
      
      
      name: "",
      
      time: "",
    
      content: "",
      
      categorys: [],
      notices: [],

      selectedCategory: null,


    };
  },
  components: {
    
  },
  computed: {
    // ...mapGetters(['userinfo', 'isLogin']),
  },
  created() {

    window.scrollTo(0,0);
    axios({
      method: "GET",
      url: `${API_URL}/user/authority/`+this.$cookies.get("yol_uid"),

    })
    .then(({data}) =>{
      this.auth=data.data;
    }).catch((err) => {
        console.log(err);
        alert("정보를 받아올때 에러가 발생했습니다.");
      });

    axios({
      method: "GET",
      url: `${API_URL}/notices/` + this.$route.params.id ,

    //   headers: {
    //     Authorization: `Token ${token}`,
    //   },
    })
      .then(({ data }) => {
        this.notice = data.data;
        
      })
      .catch((err) => {
        console.log(err);
        alert("정보를 받아올때 에러가 발생했습니다.");
      });

      
  },
  methods: {
    
    moveNotice() {
      this.$router.push({ path: "/noticelist/" });
    },
    moveNoticeModify() {
      this.$router.push({
        path: "/noticeupdate/" + this.$route.params.id,
      });
    },
    moveNoticeDelete() {
      if(confirm("정말 이 글을 삭제하시겠습니까?")){
        axios({
          method: "DELETE",
          url: `${API_URL}/notices/` + this.$route.params.id,
          // headers: {
          //   Authorization: `Token ${token}`,
          // },
        })
          .then(() => {
            this.moveNotice();
          })
          .catch((err) => {
            console.log(err);
            alert("삭제 에러가 발생했습니다.");
          });
      }
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
  .notice-detail {
    padding-top: 100px;
    background-color: #F2F2F5;
    padding-left: 15%;
    padding-right: 15%;
    padding-bottom: 5%;
    margin-bottom: -25px;

  }
</style>