<template>
<div class="total-result">
  <b-container >
    <b-row>
        <h3> 레저 / 액티비티 </h3>
    </b-row>
    <b-row>
      <carousel-3d :width="200" :height="400" controls-visible :perspective="0" :space="300" >
        <slide v-for="(leisure, i) in leisures" :key="i" :index="i">
        <div class="post-card">
          <span class="post-tag">카테고리</span>
          <div class="circle">
            <!-- <img v-bind:src="slide.logo"> -->
          </div>
          <div class="post-info">
            <div class="post-text">
              <h5>{{leisure.name}}</h5>
              <p class="post-desc">{{leisure.description}}</p>
            </div>
          </div>
        </div>
        </slide>
      </carousel-3d>
    </b-row>
    <b-row>
      <div class="more">더보기...</div>
    </b-row>
    <b-row>
        <h3> 장비 대여 </h3>
    </b-row>
    <b-row>
      <carousel-3d :width="200" :height="400" controls-visible :perspective="0" :space="300" >
        <slide v-for="(equip, i) in equips" :key="i" :index="i">
        <div class="post-card">
          <span class="post-tag">카테고리</span>
          <div class="circle">
            <!-- <img v-bind:src="slide.mainSrc"> -->
          </div>
          <div class="post-info">
            <div class="post-text">
              <h5>{{equip.name}}</h5>
              <p class="post-desc">{{equip.description}}</p>
            </div>
          </div>
        </div>
        </slide>
      </carousel-3d>
    </b-row>
    <b-row>
      <div class="more">더보기...</div>
    </b-row>
    <b-row>
      <h3> 정보 공유 </h3>
    </b-row>
    <b-row>
      <carousel-3d :width="200" :height="400" controls-visible :perspective="0" :space="300" >
        <slide v-for="(tip, i) in tips" :key="i" :index="i">
        <div class="post-card">
          <span class="post-tag">{{tip.category}}</span>
          <div class="post-tip">
            <div class="post-text">
              <h3>{{tip.title}}</h3>
              <p class="post-desc">{{tip.date}}</p>
              <p class="post-desc">{{tip.hits}}</p>
            </div>
          </div>
        </div>
        </slide>
      </carousel-3d>
    </b-row>
    <b-row>
      <div class="more">더보기...</div>
    </b-row>
  </b-container>
  </div>
</template>

<script>
import { Carousel3d, Slide } from 'vue-carousel-3d';
import axios from 'axios';

const API_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'TotalResult',
  props: {
    word: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      leisures: [
        {
          name: '업체명1',
          description: '설명설명설명설명설명설명1',
          // logo: ''
        },
        {
          name: '업체명2',
          description: '설명설명설명설명설명설명2',
          // logo: ''
        },
        {
          name: '업체명3',
          description: '설명설명설명설명설명설명3',
          // logo: ''
        },
      ],
      equips: [
        {
          name: '업체명1',
          description: '설명설명설명설명설명설명1',
          // logo: ''
        },
        {
          name: '업체명2',
          description: '설명설명설명설명설명설명2',
          // logo: ''
        },
        {
          name: '업체명3',
          description: '설명설명설명설명설명설명3',
          // logo: ''
        },
      ],
      tips: [
        {
          title: '스키타는법',
          date: 2020-10-11,
          hits: 12,
          category: '스키'
        },
        {
          title: '번지점프하는법',
          date: 2020-10-14,
          hits: 29,
          category: '번지점프'
        },
      ],
    };
  },
  components: {
    Carousel3d,
    Slide
  },
  created () {
    axios({
        method: "GET",
        url: `${API_URL}/shops/search/` + this.word,
      })
        .then((res) => {
          console.log(res.data.data);
        })
        .catch((err) => {
          console.log(err);
          alert("통합검색 에러가 발생했습니다.");
        });
  }
}
</script>

<style scoped>
  .total-result {
    padding-top: 100px;
    background-color: #F2F2F5;
  }
  .carousel-3d-slide {
    background-color: #F2F2F5;
    border-style: none;
  }
  .post-card {
    background-color: white;
    margin: 40px 0px;
    max-width: 300px;
    height: 320px;
    border-radius: 5px;
    position: relative;
    font-family: 'roboto', Sans-Serif;
    text-align: center;
    overflow: hidden;
  }
  .post-tag {
    position: absolute;
    right: -6px;
    top: 15px; 
    background-color: #FFD95B;
    font-size: 10px;
    padding: 7px;
    letter-spacing: .4px;
    text-transform: uppercase;
    box-shadow: 0px 1px 2px 0px rgba(0,0,0,0.21);
    z-index: 99;
  }
  .circle {
    height: 50%;
    width: 100%;
    background-color: darkgray;
    display: inline-block;
    position: relative;
  } 
  .circle img {
    max-width: 100%;
    max-height: 100%;
  }
  .post-info{
    display: flex;
    align-items: center;
    margin-top: 25px;
    margin-left: 5px;
  }
  .post-tip{
    display: flex;
    align-items: center;
    margin-top: 40%;
    margin-left: 5px;
  }
  .post-text {
      text-align: left;
      margin-left: 15px;
      margin-right: 15px;
  }
  .post-desc {
      margin: 4px 0;
      font-size: 12px;
  }
  .post-add {
    width: 100%;
    text-align: right;
  }
  .more {
    width: 100%;
    text-align: right;
  }
</style>
