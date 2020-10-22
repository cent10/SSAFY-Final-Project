<template>
  <div>
    <TotalSearch/>
    <Search/>
  </div>
</template>

<script>

import TotalSearch from '../components/TotalSearch.vue';
import Search from '../components/Search.vue';

export default {
  name: 'Home',
  components: {
    TotalSearch,
    Search,
  },
  data() {
    return {
      
    }
  }
}
</script>

<style scoped>
  .myhome {
    height: 100%;
  }
  .myimage {
    width: 100%;
  }
</style>