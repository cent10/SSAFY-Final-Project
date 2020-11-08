<template>
  <div class="search">
    <h1>Search</h1>
    <p>Leisure/Activity, Equipment, Tip을 찾아보세요!</p>
    <b-card-group columns class="yol-card-group">
      <b-card
        overlay
        :img-src="leisure"
        text-variant="white"
        title="레저/액티비티 업체 검색"
        class="yol-card"
        :style="cardStyle[0]"
        @mouseover="changeStyle(0)"
        @mouseleave="originalStyle(0)"
        @click="leisurelist()"
      >
      </b-card>
      <b-card
        overlay
        :img-src="equip"
        text-variant="white"
        title="장비 대여 업체 검색"
        class="yol-card"
        :style="cardStyle[1]"
        @mouseover="changeStyle(1)"
        @mouseleave="originalStyle(1)"
      >
      </b-card>
      <b-card
        overlay
        :img-src="tip"
        text-variant="white"
        title="팁 검색"
        class="yol-card"
        :style="cardStyle[2]"
        @mouseover="changeStyle(2)"
        @mouseleave="originalStyle(2)"
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
      tip,
      cardStyle: [
        {
          opacity: 1,
          "font-size": "150%",
        },
        {
          opacity: 1,
        },
        {
          opacity: 1,
        },
      ],
    };
  },
  props: {
    msg: String
  },
  methods: {
    moveTab(name) {
      this.$router.push({ name: name });
    },
    leisurelist() {
      this.$router.push({ path: "/leisurelist" });
    },
    tiplist() {
      this.$router.push({ path: "/tiplist/" });
    },
    changeStyle(i) {
      this.cardStyle[i].opacity = 0.7;
    },
    originalStyle(i) {
      this.cardStyle[i].opacity = 1;
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
  border: none;
  transition: all 0.5s;
}
.card-img {
  border-radius: 30px;
  filter: brightness(50%);
}
.card-title {
  margin-top: calc(50% - 1em);
}
</style>
