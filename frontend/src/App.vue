<template>
  <div id="app">
    <Header v-if="this.$route.path !== '/login' && !this.$route.path.includes('/kakaopay')"/>
    <router-view/>
    <Footer v-if="this.$route.path !== '/login' && !this.$route.path.includes('/kakaopay')"/>
    <b-modal id="modal-login"
      centered
      size="sm"
      title="로그인"
      header-border-variant="white"
      content-class
      hide-footer>
      <a :href="loginLink">
        <b-img id="kakao" center :src="kakao"/>
      </a>
    </b-modal>
  </div>
</template>

<script>
const KAKAO_APP_KEY = process.env.VUE_APP_KAKAO_APP_KEY;
const LOGIN_REDIRECT_URI = process.env.VUE_APP_LOGIN_REDIRECT_URI;

import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import kakao from './assets/kakao.png';

export default {
  name: 'App',
  components: {
    Header,
    Footer,
  },
  data() {
    return {
      kakao: kakao,
      loginLink: `https://kauth.kakao.com/oauth/authorize?client_id=${KAKAO_APP_KEY}&redirect_uri=${LOGIN_REDIRECT_URI}&response_type=code`,
    };
  },
  methods: {
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.modal-content {
  height: 200px
}
#kakao {
  cursor: pointer;
}
</style>
