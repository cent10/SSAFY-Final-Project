<template>
  <div class="success">
  <b-container fluid>
    <b-row align-h="center" style="margin-bottom: 30px">
      <h1>결제 성공! ^오^</h1>
    </b-row>
    <b-row align-h="center">
      <b-container fluid style="width: 50%">
        <b-row align-h="start" style="margin-bottom: 20px">
          상품명: {{this.name}}
        </b-row>
        <b-row align-h="start" style="margin-bottom: 20px">
          가격: {{this.totalPrice}}원
        </b-row>
      </b-container>
    </b-row>
    <b-button @click="toMain" style="margin-top: 20px; background-color: #084481;">메인으로</b-button>
  </b-container>
  </div>
</template>

<script>
const API_URL = process.env.VUE_APP_SERVER_URL;

import axios from 'axios';

export default {
  name: "KakaoSuccess",
  data() {
    return {
      tid: "",
      token: "",
      name: "",
      totalPrice: 0,
    };
  },
  created() {
    window.scrollTo(0,0);
    this.tid = this.$cookies.get("yol_tid");
    this.$cookies.remove("yol_tid");
    this.token = this.$route.query.pg_token;
    // 결제 승인 요청
    axios({
      method: "POST",
        url: `${API_URL}/pay/kakao/approval`,
        params: {
          tid: this.tid,
          pg_token: this.token,
        }
      }).then((res) => {
        this.name = res.data.data.item_name;
        this.totalPrice = res.data.data.amount.total;
      }).catch((err) => {
        console.log(err);
    });
  },
  methods: {
    toMain() {
      this.$router.push({name: "Home"});
    }
  }
};
</script>

<style scoped>
.success {
  margin: auto;
  padding: 5%;
  width: 50%;
  background-color: #e8e8e8;
}
</style>