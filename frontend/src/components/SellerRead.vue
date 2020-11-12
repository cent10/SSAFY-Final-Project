<template>
    <div class="tip-detail">
    <table class="table table-striped table-bordered table-hover mt-3">
      <thead>
        <tr>
          <th>서비스이름</th>
          <th>{{shop.shop.name}}</th>
        </tr>
      </thead>
      <tbody>    
        <tr>
          <td>카테고리</td>
          <td style="white-space:pre;">{{shop.category}}</td>
        </tr>
        <tr>
          <td>주소</td>
          <td style="white-space:pre;">{{shop.shop.address}}</td>
        </tr>
        <tr>
          <td>전화번호</td>
          <td style="white-space:pre;">{{shop.shop.phone}}</td>
        </tr>
        <tr>
          <td>지역</td>
          <td style="white-space:pre;">{{shop.shop.region}}</td>
        </tr>
        <tr>
          <td>설명</td>
          <td style="white-space:pre;">{{shop.shop.description}}</td>
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

import axios from "axios";

export default {
  name: "SellerRead",
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
      url: `${API_URL}/shops/` + this.$route.params.id ,

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

<style>

</style>