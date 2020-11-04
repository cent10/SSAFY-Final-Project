<template>
  <b-navbar
    :class="{ 'yol-header': true, 'yol-header-bg': check() }"
    fixed="top"
    type="dark"
  >
    <b-navbar-brand href="/"> 야! 올레? </b-navbar-brand>
    <b-navbar-nav class="ml-auto">
      <b-nav-item @click="noticelist()"> 공지사항 </b-nav-item>
      <b-nav-item v-b-modal.modal-login> 로그인 </b-nav-item>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
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
    noticelist() {
      this.$router.push({ path: "/noticelist/" });
    },
  },
  mounted() {
    window.addEventListener("scroll", this.updateScroll);
    if (this.$route.path === "/") this.isMain = true;
    else this.isMain = false;
  },
  watch: {
    "$route.path"(to) {
      console.log(to);
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