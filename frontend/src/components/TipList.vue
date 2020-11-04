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
        <option v-for="(onecategory,id) in categorys" :key="id" :value="onecategory.category">{{onecategory.category}}</option>
      </select>

    </div>
    <p />
    <p></p>
    <table class="table table-striped table-bordered table-hover">
      <thead>
        <tr>
          <th style="width:20%;">카테고리</th>
          <th style="width:20%;">제목</th>
          <th style="width:20%;">내용</th>
          <th style="width:15%;">작성자</th>
          <th style="width:15%;">날짜</th>
          <th style="width:10%;">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(notice,id) in notices"
          :key="id"
          
          @click="moveDetail(notice.id)"
        >
          <td>{{notice.category.slice(0, 6)}}</td>
          <td>{{notice.title.slice(0, 8)}}</td>
          <td>{{notice.content.slice(0, 7)}}</td>
          <td>{{notice.user}}</td>
          <td>{{notice.date}}</td>
          <td>{{notice.hits}}</td>

        </tr>
      </tbody>
    </table>
    <p />
  </div>
</template>

<script>
/*jslint smarttabs:true */
// import sessionManager from "@/components/SessionManager.js";
const API_URL = process.env.VUE_APP_SERVER_URL;

// import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "TipList",
  props: {
    word: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      key: "1",
      orderTitle: 0,
      id: "",

      s: "all",
      g: "all",

      title: "",
      
      no: "",

      categorys: [],
      notices: [],
      
      name: "",
      
      time: "",
  
      content: "",
      
      selectedCategory: null,
    };
  },
  components: {},
  computed: {
    // ...mapGetters(['userinfo', 'isLogin']),
  },
  created() {
    axios
      .get(`${API_URL}/tip/category`)
      .then(({ data }) => {
        this.categorys = data;
      })
      .catch((err) => {
        alert("정보를 받아올때 에러가 발생했습니다.");
        console.log(err);
      });

    
    axios
      .get(`${API_URL}/tip/all`)
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
      var category = (this.selectedCategory == "null") ? null : this.selectedCategory
    
      const data =  {
        'category': category,
      }
      // const token = this.$cookies.get('auth-token')
      axios.post(`${API_URL}/tip/search/`, data)
      .then((res) => {
        this.notices = res.data
      })
      .catch((err) => {
        alert("정보를 받아올때 에러가 발생했습니다.");
        console.log(err)
      })
    },
    moveDetail(id) {
      axios.get(`${API_URL}/tip/increase/`+ id)
      .catch((err) =>{
        console.log(err)
      })
      this.$router.push({ path: "/tip/detail/" + id });
    },
    // keyWord(city, gu, kind) {
      // console.log(city);
      // console.log(gu);
      // console.log(kind);
      // console.log(this.selectedCity);
      // console.log(this.selectedGu);
      // console.log(this.selectedKind);

    //   if (
    //     city === this.selectedCity &&
    //     gu === this.selectedGu &&
    //     kind === this.selectedKind
    //   ) {
    //     return true;
    //   } else if (
    //     this.selectedCity == null &&
    //     this.selectedGu == null &&
    //     this.selectedKind == null
    //   ) {
    //     return true;
    //   }

    //   return false;
    // },
  },
  filter: {},
};
</script>