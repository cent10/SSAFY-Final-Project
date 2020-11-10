<template>
  <div>
    결제 성공!

    상품명: {{this.name}}
    가격: {{this.totalPrice}}원
    <b-button @click="toMain">메인으로</b-button>
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
</style>