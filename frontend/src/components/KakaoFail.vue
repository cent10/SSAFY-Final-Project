<template>
  <div style="text-align: center; padding: 100px">
    헐... 결제 실패...
    <div> 
      <b-button @click="toMain" style="margin: 20px; background-color: #084481;">메인으로</b-button>
    </div> 
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
    window.scrollTo(0,0);
    this.$cookies.remove("yol_purchasingList");
  },
  methods: {
    toMain() {
      this.$router.push({name: "Home"});
    }
  }
};
</script>