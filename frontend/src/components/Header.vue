<template>
  <b-navbar
    :class="{ 'yol-header': true, 'yol-header-bg': check() }"
    fixed="top"
    type="dark"
  >
    <b-navbar-brand href="/"> 야! 올레? </b-navbar-brand>
    <b-navbar-nav class="ml-auto">
      <b-nav-item @click="movemyprofile()"> 너의 정보가 궁금해!? </b-nav-item>
      <b-nav-item @click="noticelist()"> 공지사항 </b-nav-item>
      <b-nav-item v-b-modal.modal-login v-if="!this.$cookies.isKey('yol_token')"> 로그인 </b-nav-item>
      <b-nav-item @click="logout" v-else> 로그아웃 </b-nav-item>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
// const API_URL = process.env.VUE_APP_SERVER_URL;

// import axios from 'axios';

export default {
  name: "Header",
  data() {
    return {
      scrollPosition: null,
      isMain: true,
    };
  },
  methods: {
    check() {
      if (this.scrollPosition < 100 && this.isMain) return false;
      else return true;
    },
    updateScroll() {
      this.scrollPosition = window.scrollY;
    },
    movemyprofile(){
      this.$router.push({ path: "/myprofile" });
    },
    noticelist() {
      this.$router.push({ path: "/noticelist/" });
    },
    logout() {
      
            this.$cookies.remove("yol_nickname");
            this.$cookies.remove("yol_token");
            this.$cookies.remove("yol_ukey");
        // axios({
        //     method: "GET",
        //     url: `${API_URL}/logout`,
        //     params: {
        //         code: this.$route.query.code
        //     }
        // })
        // .then(() => {
        //     this.$cookies.remove("yol_nickname");
        //     this.$cookies.remove("yol_token");
        //     this.$cookies.remove("yol_ukey");
        // })
        // .catch((err) => {
        //     console.log(err);
        //     alert("로그인 과정 중 에러가 발생했습니다.");
        // });
      this.$router.go(this.$router.currentRoute);
    }
  },
  mounted() {
    window.addEventListener("scroll", this.updateScroll);
    if (this.$route.path === "/") this.isMain = true;
    else this.isMain = false;
  },
  watch: {
    "$route.path"(to) {
      if (to === "/") this.isMain = true;
      else this.isMain = false;
    },
  },
};
</script>

<style scoped>
.yol-header {
  height: 65px;
  padding-left: 15%;
  padding-right: 15%;
  transition: background-color 0.3s;
}
.yol-header-bg {
  background-color: rgba(1, 1, 1, 0.7);
  transition: background-color 0.3s;
}
</style>