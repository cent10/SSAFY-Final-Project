<template>
  <div id="app">
    <Header/>
    <router-view/>
    <Footer/>
    <b-modal id="modal-login"
      centered
      size="sm"
      title="로그인"
      header-border-variant="white"
      content-class
      hide-footer>
      <a href="https://kauth.kakao.com/oauth/authorize
            ?client_id=c917624215999ace922acc8e48ce073e
            &redirect_uri=http://k3a210.p.ssafy.io/login
            &response_type=code">
        <b-img id="kakao" center :src="kakao"/>
      </a>
    </b-modal>
  </div>
</template>

<script>
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
    };
  }
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
