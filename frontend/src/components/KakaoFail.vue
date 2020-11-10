<template>
  <div>
    헐... 결제에 실패했어요..
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
  }
};
</script>

<style scoped>
</style>