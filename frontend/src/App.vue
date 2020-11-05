<template>
  <div id="app">
    <Header v-if="this.$route.path !== '/login'"/>
    <router-view/>
    <Footer v-if="this.$route.path !== '/login'"/>
    <b-modal id="modal-login"
      centered
      size="sm"
      title="로그인"
      header-border-variant="white"
      content-class
      hide-footer>
      <a href="https://kauth.kakao.com/oauth/authorize?client_id=c917624215999ace922acc8e48ce073e&redirect_uri=http://localhost:8080/login&response_type=code">
        <b-img id="kakao" center :src="kakao"/>
      </a>
    </b-modal>
    
        {{this.$cookies.get("yol_nickname")}}
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
