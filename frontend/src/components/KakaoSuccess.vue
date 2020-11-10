<template>
  <div>
    결제 성공!
  </div>
</template>

<script>
// const API_URL = process.env.VUE_APP_SERVER_URL;

// import axios from 'axios';

export default {
  name: "KakaoSuccess",
  data() {
    return {
      tid: "",
      token: "",
    };
  },
  created() {
    this.tid = this.$cookies.get("yol_tid");
    this.$cookies.remove("yol_tid");
    this.token = this.$route.query.pg_token;
    console.log(this.tid);
    console.log(this.token);
    // 결제 승인 요청
    // axios({
    //   method: "POST",
    //     url: `${API_URL}/pay/kakao/approval`,
    //     params: {
    //       tid: this.tid,
    //       token: this.token,
    //     }
    //   }).then((res) => {
    //     console.log(res);
    //   }).catch((err) => {
    //     console.log(err);
    // });
  }
};
</script>

<style scoped>
</style>