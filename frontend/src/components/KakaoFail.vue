<template>
  <div>
    헐... 결제 실패...
    <b-button @click="toMain">메인으로</b-button>
  </div>
</template>

<script>
// const API_URL = process.env.VUE_APP_SERVER_URL;

// import axios from 'axios';

export default {
  name: "KakaoFail",
  data() {
    return {
    };
  },
  created() {
    this.$cookies.remove("yol_purchasingList");
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