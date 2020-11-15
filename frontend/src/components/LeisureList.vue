<template>
<div>
<video-bg :sources="[require('../assets/leisureBg.mp4')]" class="stuck">
</video-bg>
  <div class="leisure-list">
    <b-card class="condition-card">
      <b-container fluid>
        <b-row class="condition">
          <b-col>
            <h5>가격 범위</h5>
            <div class="price">
              <b-form-input type="number" v-model="totalCondition.minPrice"/>
              <div class="price-character">원</div>
              <div class="price-character">~</div>
              <b-form-input type="number" v-model="totalCondition.maxPrice"/>
              <div class="price-character">원</div>
            </div>
            <p style="font-size: 0.2rem; margin: 0; margin-top: 0.1rem; text-align: right;">* 가장 저렴한 상품 가격 기준</p>
          </b-col>
          <b-col>
            <h5>지역</h5>
            <b-form-select v-model="totalCondition.selectedRegion">
              <b-form-select-option value="null">모두</b-form-select-option>
              <b-form-select-option v-for="(region,id) in regions" :key="id" :value="region">{{region}}</b-form-select-option>
            </b-form-select>
          </b-col>
          <b-col>
            <h5>카테고리</h5>
            <b-form-select v-model="totalCondition.selectedCategory">
              <b-form-select-option value="null">모두</b-form-select-option>
              <b-form-select-option v-for="(onecategory,id) in categorys" :key="id" :value="onecategory.category">{{onecategory.category}}</b-form-select-option>
            </b-form-select>
          </b-col>
        </b-row>
        <b-row class="search-button">
          <b-button @click="search()" style="background-color: #084481;">검색</b-button>
        </b-row>
      </b-container>
    </b-card>
      <div class="order-condition">
        <b-form-select v-model="selectedCondtion">
          <b-form-select-option value="null">최신순</b-form-select-option>
          <b-form-select-option v-for="(condition,id) in conditions" :key="id" :value="condition">{{condition}}</b-form-select-option>
        </b-form-select>
      </div>
      <b-card-group deck v-if="leisures !== null && leisures.length > 0">
        <b-card v-for="(leisure, i) in leisures" :key="i" :index="i" @click="viewLeisure(i)">
          <div class="post-card">
            <span class="post-tag">{{leisure.region}}</span>
            <div class="logo">
              <img v-bind:src="leisure.img">
            </div>
            <div class="post-info">
              <div class="post-text">
                <h5>{{leisure.name}}</h5>
                <p class="post-desc">{{leisure.description}}</p>
              </div>
            </div>
          </div>
        </b-card>
      </b-card-group>
      <b-card-group deck v-else>
        <div class="no-search" >
          <img :src="empty"/>
          <h3>검색결과 없음</h3>
        </div>
      </b-card-group>
      <infinite-loading @infinite="infiniteHandler" ref="infiniteLoading" spinner="waveDots">
        <div slot="no-more"></div>
        <div slot="no-results"></div>
      </infinite-loading>
    </div>
  </div>
</template>

<script>
const API_URL = process.env.VUE_APP_SERVER_URL;

import InfiniteLoading from 'vue-infinite-loading';
import axios from 'axios';

import empty from '../assets/empty.png';
import VideoBg from 'vue-videobg'

export default {
  name: "LeisureList",
  components: {
    InfiniteLoading,
    VideoBg
  },
  data() {
    return {
      getList: (con, num) => {
        this.requestCondition.num = num;
        switch(con){
          case "가격낮은순":
            axios({
              method: "GET",
              url: `${API_URL}/shops/detailsearch/leisureshop/orderbyprice`,
              params: this.requestCondition,
            }).then((res) => {
                if(res.data.data === null){
                  this.leisures = [];
                  return;
                }
                this.leisures = res.data.data;
                this.leisures.map((l) => {
                  if(l.img !== null)
                    l.img = "http://k3a210.p.ssafy.io/img/activityx_shop_img/" + l.img;
                });
              })
              .catch((err) => {
                alert("업체 정보를 받아올때 에러가 발생했습니다.");
                console.log(err);
              });
            break;
          case "평점높은순":
            axios({
              method: "GET",
              url: `${API_URL}/shops/detailsearch/leisureshop/orderbyrate`,
              params: this.requestCondition,
            }).then((res) => {
                if(res.data.data === null){
                  this.leisures = [];
                  return;
                }
                this.leisures = res.data.data;
                this.leisures.map((l) => {
                  if(l.img !== null)
                    l.img = "http://k3a210.p.ssafy.io/img/activityx_shop_img/" + l.img;
                });
              })
              .catch((err) => {
                alert("업체 정보를 받아올때 에러가 발생했습니다.");
                console.log(err);
              });
            break;
          default :
            axios({
              method: "GET",
              url: `${API_URL}/shops/detailsearch/leisureshop`,
              params: this.requestCondition,
            }).then((res) => {
                if(res.data.data === null){
                  this.leisures = [];
                  return;
                }
                this.leisures = res.data.data;
                this.leisures.map((l) => {
                  if(l.img !== null)
                    l.img = "http://k3a210.p.ssafy.io/img/activityx_shop_img/" + l.img;
                });
              })
              .catch((err) => {
                alert("업체 정보를 받아올때 에러가 발생했습니다.");
                console.log(err);
              });
            break;
        }
      },
      leisures: [
      ],
      regions: [
        "서울",
        "부산",
        "대구",
        "인천",
        "광주",
        "대전",
        "울산",
        "경기",
        "강원",
        "충북",
        "충남",
        "전북",
        "전남",
        "경북",
        "경남",
        "제주",
        "세종",
      ],
      categorys: [
      ],
      conditions: [
        "가격낮은순",
        "평점높은순",
      ],

      selectedCondtion: null,

      totalCondition: {
        minPrice: 0,
        maxPrice: 99999999,
        selectedRegion: null,
        selectedCategory: null,
      },

      requestCondition: {
        num: 0,
      },

      empty,
    };
  },
  created() {
    window.scrollTo(0, 0);

    axios
      .get(`${API_URL}/category/all`)
      .then((res) => {
        this.categorys = res.data.data;
      })
      .catch((err) => {
        alert("카테고리 정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });

    axios({
      method: "GET",
      url: `${API_URL}/shops/detailsearch/leisureshop`,
      params: {
        num: 0,
      }
    }).then((res) => {
        this.leisures = res.data.data;
        this.leisures.map((l) => {
          if(l.img !== null)
            l.img = "http://k3a210.p.ssafy.io/img/activityx_shop_img/" + l.img;
        });
      })
      .catch((err) => {
        alert("업체 정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });

  },
  watch: {
    selectedCondtion: function(to) {
      this.getList(to, 0);
      window.scrollTo(0, 0);
      this.$refs.infiniteLoading.stateChanger.reset()
    },
  },
  methods: {
    search(){
      this.requestCondition = {};
      this.requestCondition.minPrice = this.totalCondition.minPrice;
      this.requestCondition.maxPrice = this.totalCondition.maxPrice;
      if(this.totalCondition.selectedRegion !== null && this.totalCondition.selectedRegion !== "null")
        this.requestCondition.region = this.totalCondition.selectedRegion;
      if(this.totalCondition.selectedCategory !== null && this.totalCondition.selectedCategory !== "null")
        this.requestCondition.category = this.totalCondition.selectedCategory;

      this.getList(this.selectedCondtion, 0);

      window.scrollTo(0, 0);
      this.$refs.infiniteLoading.stateChanger.reset()
    },
    viewLeisure(i) {
      this.$router.push({ path: `/leisuredetail/${this.leisures[i].id}`});
    },
    infiniteHandler($state) {
      this.requestCondition.num += 1;
      axios({
        method: "GET",
      url: `${API_URL}/shops/detailsearch/leisureshop`,
      params: this.requestCondition,
      }).then(res => {
        setTimeout(() => {
          if(res.data.status && res.data.data.length) {
            this.leisures = this.leisures.concat(res.data.data)
            $state.loaded()
            if(res.data.data.length / 12 < 1) {
              $state.complete()
            }
          } else {
            $state.complete()
          }
        }, 1000)
      }).catch(err => {
        console.error(err);
      })
    }
  }
};
</script>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
.stuck {
  filter: brightness(60%);
  position: fixed;
  width: 100%;
  z-index: 1;
}
.leisure-list {
  position: relative;
  padding-top: 100px;
  padding-left: 15%;
  padding-right: 15%;
  z-index: 99;
}
.no-search {
  position: relative;
  width: 100%;
  color: white;
  height: 400px;
}
.no-search img {
  width: 100px;
  height: 100px;
  margin-top: 7%;
  margin-bottom: 7%;
}
.condition-card {
  background-color: #f9f7f7;
  border-radius: 10px;
}
.price {
  display: flex;
}
.price-character {
  padding: 5px;
}
.order-condition{
  position: relative;
  display: flex;
  margin-top: 5%;
  margin-left: 80%;
  padding-right: 30px;
  width: 20%;
}
.condition {
  padding-top: 2%;
  padding-left: 3%;
  padding-right: 3%;
}
.search-button{
  padding: 2% 0;
  margin-right: 5%;
  justify-content: flex-end;
}
.card-deck {
  padding: 3%;
}
.card-deck .card {
  margin-bottom: 3%;
  flex: none;
  width: calc(25% - 30px);
  height: 400px;
  cursor: pointer;
  background-color: #f9f7f7;
  transition: all 0.5s;
  opacity: 0.85;
}
.card-deck .card:hover {
  transform: scale(1.05);
  opacity: 1;
}
.post-card {
    background-color: #f9f7f7;
    width: 100%;
    height: 100%;
    border-radius: 5px;
    position: relative;
    font-family: 'roboto', Sans-Serif;
    text-align: center;
    overflow: hidden;
    opacity: 0.8;
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
  .logo {
    height: 50%;
    width: 100%;
    background-color: #495464;
    display: inline-block;
    position: relative;
  } 
  .logo img {
    width: 100%;
    height: 100%;
    object-fit: fill;
  }
  .post-info{
    display: flex;
    align-items: center;
    margin-top: 25px;
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
</style>