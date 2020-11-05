<template>
  <div class="total-search">
    <b-carousel
      v-model="slide"
      :interval="4000"
      background="#ababab"
      class="yol-carousel"
    >
      <b-carousel-slide :img-src="bg2" />
      <b-carousel-slide :img-src="bg3" />
      <b-carousel-slide :img-src="bg4" />
      <b-carousel-slide :img-src="bg5" />
      <b-carousel-slide :img-src="bg1" />
      <b-carousel-slide :img-src="bg6" />
      <b-carousel-slide :img-src="bg7" />
      <b-carousel-slide :img-src="bg8" />
    </b-carousel>
    <div class="search-jumbotron">
      <b-jumbotron
        header="야! 올레?"
        header-level="2"
        text-variant="white"
        bg-variant="transparent"
      >
        <p>#번지점프 #행글라이더 #스키장비</p>
        <b-nav-form>
          <b-form-input
            size="lg"
            class="mr-sm-2"
            v-model="word"
            @keydown.enter="search"
          ></b-form-input>
          <b-button
            size="lg"
            class="my-2 my-sm-0"
            variant="dark"
            @click="search"
            >검색</b-button
          >
        </b-nav-form>
      </b-jumbotron>
    </div>
  </div>
</template>

<script>
import bg1 from "../assets/bg1.jpg";
import bg2 from "../assets/bg2.jpg";
import bg3 from "../assets/bg3.jpg";
import bg4 from "../assets/bg4.jpg";
import bg5 from "../assets/bg5.jpg";
import bg6 from "../assets/bg6.jpg";
import bg7 from "../assets/bg7.jpg";
import bg8 from "../assets/bg8.jpg";

export default {
  name: "TotalSearch",
  data: function () {
    return {
      slide: 0,
      bg1: bg1,
      bg2: bg2,
      bg3: bg3,
      bg4: bg4,
      bg5: bg5,
      bg6: bg6,
      bg7: bg7,
      bg8: bg8,
      word: "",
    };
  },
  methods: {
    search: function () {
      if (this.word === "") alert("검색어를 입력해주세요!");
      else this.$router.push({ path: `totalresult/${this.word}` });
    },
  },
};
</script>

<style scoped>
li {
  display: inline-block;
}
.total-search {
  background-color: transparent;
  position: relative;
}
.yol-carousel {
  z-index: -99;
  filter: brightness(80%);
}
.search-jumbotron {
  z-index: 1;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
