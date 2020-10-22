<template>
  <div class="total-search">
    <b-carousel
        v-model="slide"
        :interval="5000"
        background="#ababab"
        >
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=52" class="slide"/>
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54" class="slide"/>
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58" class="slide"/>
        <div>
            <b-jumbotron header="야! 올레?" lead="레저를 검색해보세요" text-variant="white"  bg-variant="transparent">
              <b-nav-form>
                  <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                  <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
              </b-nav-form>
            </b-jumbotron>
        </div>
    </b-carousel>
  </div>
</template>

<script>
export default {
  name: 'TotalSearch',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
li {
  display: inline-block;
  margin: 0 10px;
}
.slide {
  z-index: -99;
}
</style>
