<template>
  <span>
    <i v-for="(n, i) in maxStars"
        :key="i"
       :class="getClass(n)" 
       :style="getStyle(n)"></i>
  </span>
  
</template>

<script>
export default {
    name: "StarRating",
    props:{
        value:{type: Number, default: 0},
        maxStars: {type: Number, default: 5},
        starredColor: {type: String, default: "orange"},
        blankColor: {type: String, default: "darkgray"},
    },
    methods:{
        getClass(n){
            return {
                "fa": true,
                "fa-star": n <= this.value,
                "fa-star-o": n > this.value,
                "fa-2x": true,
            }
        },
        getStyle(n){
            return {
                color: n <= this.value ? this.starredColor : this.blankColor
            }
        }
    }
}
</script>