<template>
  <div>
    <h2>팁 게시판</h2>
    <hr />
    <div class="d-flex justify-content-between">
      <select
        name="category"
        id="category"
        class="form-control"
        title="카테고리"
        v-model="selectedCategory"
        @change="getArticles"
      >
        <option value="null">카테고리</option>
    	
        <option v-for="(onecategoty,id) in categorys" :key="id" :value="onecategory.category">{{onecategory.category}}</option>
      </select>
    <p />
    <p></p>
    <table class="table table-striped table-bordered table-hover">
      <thead>
        <tr>
          <th style="width:25%;">카테고리</th>
          <th style="width:25%;">제목</th>
          <th style="width:25%;">날짜</th>
          <th style="width:25%;">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(notice,id) in notices"
          :key="id"
          
          @click="moveDetail(notice.id)"
        >
          <td>{{notice.city.city.slice(0, 2)}}</td>
          <td>{{notice.borough.gu.slice(0, 3)}}</td>
          <td>{{notice.animal.kind}}</td>
          <td>{{notice.title.slice(0, 5)}}</td>
        </tr>
      </tbody>
    </table>
    <p />
  </div>
</template>

<script>
// import sessionManager from "@/components/SessionManager.js";
const API_URL = process.env.VUE_APP_SERVER_URL;

import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "TipList",
  data() {
    return {
      key: "1",
      word: "",
      orderTitle: 0,
      id: "",

      s: "all",
      g: "all",

      title: "",
      gu: "",
      citys: [],
      no: "",

      categorys: [],
      gugun: [],
      animals: [],
      breeds: [],
      name: "",
      gender: "",
      age: "",
      time: "",
      img_path: "",
      content: "",
      selectedCity: null,
      selectedGu: null,
      selectedKind: null,
    };
  },
  components: {},
  computed: {
    // ...mapGetters(['userinfo', 'isLogin']),
  },
  created() {
    axios
      .get(`${API_URL}/*****`)
      .then(({ data }) => {
        this.categorys = data;
      })
      .catch((err) => {
        alert("정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });

    
    axios
      .get(`${API_URL}/articles_find/`)
      .then(({ data }) => {
        this.notices = data;
        //   console.log(this.data)
        //   this.comments = notices.comments;
        //   console.log(comments)
      })
      .catch((err) => {
        alert("정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });
  },
  methods: {
    getArticles() {
      var city = (this.selectedCity == "null") ? null : this.selectedCity
      var borough = (this.selectedGu == "null") ? null : this.selectedGu
      var animal = (this.selectedKind == "null") ? null : this.selectedKind
      const data =  {
        'city': city,
        'borough': borough,
        'animal': animal
      }
      const token = this.$cookies.get('auth-token')
      axios.post(`${API_URL}/articles_find/search/`, data)
      .then((res) => {
        this.notices = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    },
    moveDetail(id) {
      this.$router.push({ path: "/articles_find/" + id });
    },
    keyWord(city, gu, kind) {
      // console.log(city);
      // console.log(gu);
      // console.log(kind);
      // console.log(this.selectedCity);
      // console.log(this.selectedGu);
      // console.log(this.selectedKind);

      if (
        city === this.selectedCity &&
        gu === this.selectedGu &&
        kind === this.selectedKind
      ) {
        return true;
      } else if (
        this.selectedCity == null &&
        this.selectedGu == null &&
        this.selectedKind == null
      ) {
        return true;
      }

      return false;
    },
  },
  filter: {},
};
</script>