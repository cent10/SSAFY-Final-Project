<template>
  <div class="notice-detail">
    <table class="table table-striped table-bordered table-hover mt-3">
      <thead>
        <tr>
          <th>제목</th>
          <th>{{notice.title}}</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>작성자</td>
          <td style="white-space:pre;">{{notice.user}}</td>
        </tr>
        <tr>
          <td>날짜</td>
          <td style="white-space:pre;">{{notice.date.slice(0,10)}}</td>
        </tr>
        <tr>
          <td>내용</td>
          <td style="white-space:pre;">{{notice.content}}</td>
        </tr>
        <tr>
          <td>조회수</td>
          <td style="white-space:pre;">{{notice.hits}}</td>
        </tr>
      </tbody>
    </table>
    
    <!-- <b-button v-if="notice.user == user" class="mybutton1" @click="moveNoticeModify()">수정</b-button> -->
    <b-button class="mybutton1" @click="moveNoticeModify()">수정</b-button>

    <!-- <b-button  v-if="notice.user == user" class="mybutton2 mx-3" @click="moveNoticeDelete()">삭제</b-button> -->
    <b-button class="mybutton2 mx-3" @click="moveNoticeDelete()">삭제</b-button>

    <b-button class="border-0" @click="moveNotice()">목록으로</b-button>

    
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
      datas: {},
      id: "",
      comment: "",
      title: "",
      
      
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
    // const token = this.$cookies.get("auth-token");
    // console.log(token);

    axios({
      method: "GET",
      url: `${API_URL}/notices/` + this.$route.params.id ,

    //   headers: {
    //     Authorization: `Token ${token}`,
    //   },
    })
      .then(({ data }) => {
        console.log(data);
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
    //   const token = this.$cookies.get("auth-token");
    //   console.log(token);
    //   console.log();
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
  }
</style>