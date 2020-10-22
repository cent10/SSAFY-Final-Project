<template>
  <b-navbar fixed="top" type="dark" variant="secondary" class="myheader">
    <b-navbar-brand href="#">
      야! 올레?
    </b-navbar-brand>
    <b-navbar-nav class="ml-auto">
        <b-nav-item>
            공지사항
        </b-nav-item>
        <b-nav-item>
            로그인
        </b-nav-item>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
export default {
    name: "Header",
}
</script>

<style scoped>
  .myheader {
      height: 60px;
  }
</style>