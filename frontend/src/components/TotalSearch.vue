<template>
  <div class="total-search">
    <b-carousel
      v-model="slide"
      :interval="3000"
      background="#ababab"
      class="yol-carousel"
    >
      <b-carousel-slide :img-src="bgs[0]" />
      <b-carousel-slide :img-src="bgs[1]" />
      <b-carousel-slide :img-src="bgs[2]" />
      <b-carousel-slide :img-src="bgs[3]" />
      <b-carousel-slide :img-src="bgs[4]" />
      <b-carousel-slide :img-src="bgs[5]" />
      <b-carousel-slide :img-src="bgs[6]" />
      <b-carousel-slide :img-src="bgs[7]" />
    </b-carousel>
    <div class="search-jumbotron">
      <b-jumbotron
        fluid
        header="야! 올레?"
        header-level="3"
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
      bgs: [
        bg1,
        bg2,
        bg3,
        bg4,
        bg5,
        bg6,
        bg7,
        bg8,
      ],
      word: "",
    };
  },
  created() {
    for (let i = this.bgs.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [this.bgs[i], this.bgs[j]] = [this.bgs[j], this.bgs[i]];
    }
  },
  methods: {
    search: function () {
      if (this.word === "") alert("검색어를 입력해주세요!");
      else this.$router.push({ path: `totalresult/${this.word}` });
      window.scrollTo(0,0);
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
  filter: brightness(65%);
}
.search-jumbotron {
  z-index: 4;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.display-3 {
  font-weight: 600;
}
</style>
