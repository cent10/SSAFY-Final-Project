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
        <b-button type="button" @click="modify" style="background-color: #084481;">
          수정
        </b-button>
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
    window.scrollTo(0, 0);
  },
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
        .then((data) => {
          if(data.data.status){
              this.$cookies.set("yol_nickname", this.name);
              this.$router.push({ path: `/` });
              alert(
                "닉네임 변경에 성공했습니다."
              );
          } else{
              alert(
                data.data.msg
              );
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
  padding-top: 120px;
  margin-bottom: 17%;
}
.table {
  font-size: 30px;
}
table td {
  min-width: 150px;
  border: none;
}
</style>