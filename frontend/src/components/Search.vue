<template>
  <div class="search">
    <b-card-group columns>
      <b-card title="레저/액티비티 업체 찾기" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top/>
      <b-card title="장비 대여 업체 찾기" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top/>
      <b-card @click="moveTab('TipList')" title="팁" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top/>
    </b-card-group>
  </div>
</template>

<script>
export default {
  name: 'Search',
  props: {
    msg: String
  },
  methods: {
    moveTab(name) {
      this.$router.push({ name: name });
    },
  }
}
</script>

<style scoped>
.search {
  padding: 100px;
}
</style>
