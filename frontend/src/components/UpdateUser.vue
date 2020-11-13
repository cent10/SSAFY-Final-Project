<template>
  <div class="update-user" align="center">
    <tr class="table">
      <td>nickname</td>
      <td>
        <input v-model="name" />
      </td>
    </tr>
    <tr>
      <td></td>
      <td>
        <button type="button" class="btn btn-primary" @click="modify">
          수정
        </button>
      </td>
      <td></td>
    </tr>
  </div>
</template>

<script>
import axios from "axios";

const API_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "UpdateUser",
  data() {
    return {
      name: this.$cookies.get("yol_nickname"),
    };
  },
  created() {
    //   axios
    //   .get(`${API_URL}/tip/all`)
    //   .then(({ data }) => {
    //     console.log(data)
    //     this.notices = data.data;
    //     //   console.log(this.data)
    //     //   this.comments = notices.comments;
    //     //   console.log(comments)
    //   })
    //   .catch((err) => {
    //     alert("정보를 받아올때 에러가 발생했습니다.");
    //     console.log(err);
    //   });
  },
  components: {},
  methods: {
    modify() {
      axios({
        method: "PUT",
        url: `${API_URL}/user/modify`,
        data: {
          id: this.$cookies.get("yol_uid"),
          name: this.name,
          ukey: this.$cookies.get("yol_ukey"),
        },
        // headers:{
        //   'Authorization':`Token ${token}`
        // },
      })
        .then(({ data }) => {
          console.log(data);
          if(data.status){
            this.$router.push({ path: `/` });
            alert(
              "닉네임 변경에 성공했습니다."
            );
          }
          else{
            alert(
              data.msg
            )
          }
        })
        .catch((err) => {
          alert(
            "수정할때 에러가 발생했습니다. \n 입력값을 다시 한번 확인해주세요!"
          );
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
.update-user {
  margin-top: 100px;
  margin-bottom: 17%;
}
.table {
  font-size: 30px;
}
td {
  min-width: 150px;
}
</style>