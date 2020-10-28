<template>
  <b-navbar class="yol-header" fixed="top" type="dark">
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
  .yol-header  {
      height: 65px;
      background-color: rgba(1, 1, 1, 0.5);
      padding-left: 15%;
      padding-right: 15%;
  }
</style>