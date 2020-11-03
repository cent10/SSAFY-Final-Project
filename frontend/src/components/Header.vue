<template>
  <b-navbar class="yol-header" fixed="top" type="dark">
    <b-navbar-brand href="/">
      야! 올레?
    </b-navbar-brand>
    <b-navbar-nav class="ml-auto">
        <b-nav-item @click="noticelist()">
            공지사항
        </b-nav-item>
        <b-nav-item v-b-modal.modal-login>
            로그인
        </b-nav-item>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
export default {
    name: "Header",
    data() {
      return{
        scrollPosition: null
      }
     },
     methods: {
       updateScroll() {
         this.scrollPosition = window.scrollY
       },
       noticelist(){
          this.$router.push({ path: "/noticelist/" });
       }
     },
     mounted() {
       window.addEventListener('scroll', this.updateScroll);
     }
}
</script>

<style scoped>
  .yol-header {
      height: 65px;
      padding-left: 15%;
      padding-right: 15%;
      background-color: black;
  }
  .yol-header-bg {
      background-color: rgba(1, 1, 1, 0.5);
  }
</style>