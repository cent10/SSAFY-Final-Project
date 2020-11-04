<template>
  <div class="search">
    <h2>Search</h2>
    <p>Leisure/Activity, Equipment, Tip을 찾아보세요!</p>
    <b-card-group columns class="yol-card-group">
      <b-card
        overlay
        :img-src="leisure"
        text-variant="white"
        title="레저/액티비티 업체 찾기"
        class="yol-card"
      >
      </b-card>
      <b-card
        overlay
        :img-src="equip"
        text-variant="white"
        title="장비 대여 업체 찾기"
        class="yol-card"
      >
      </b-card>
      <b-card
        overlay
        :img-src="tip"
        text-variant="white"
        title="팁"
        class="yol-card"
        @click="tiplist()"
      >
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
import leisure from '../assets/leisure.jpg';
import equip from '../assets/equipment.jpg';
import tip from '../assets/tip.jpg';

export default {
  name: 'Search',
  data: function() {
    return {
      leisure,
      equip,
      tip
    };
  },
  props: {
    msg: String
  },
  methods: {
    moveTab(name) {
      this.$router.push({ name: name });
    },
    tiplist(){
      this.$router.push({ path: "/tiplist/" });
    }
  }
}
</script>

<style scoped>
.search {
  padding: 100px;
}
.yol-card-group {
  padding-top: 50px;
}
.yol-card {
  width: 100%;
  height: 100%;
  cursor: pointer;
}
.yol-title {
}
</style>
