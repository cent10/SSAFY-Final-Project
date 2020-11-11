<template>
  <div>
  <div class="pay-info">
    <b-container fluid>
      <!-- <b-row align-h="center">
        <h1>결제페이지</h1>
      </b-row> -->
      <b-row align-h="center" class="pay-row">
        <h2>구매 정보</h2>
      </b-row>
      <b-row align-h="center" class="pay-row">
        <div>
          <label for="start-date">시작 날짜</label>
          <b-form-datepicker :date-disabled-fn="startDateDisabled" hide-header id="start-date" v-model="startDate"></b-form-datepicker>
        </div>
        <div>
          <label for="end-date">끝 날짜</label>
          <b-form-datepicker :disabled="startDate === ''" :date-disabled-fn="endDateDisabled" hide-header id="end-date" v-model="endDate"></b-form-datepicker>
        </div>
      </b-row>
      <b-row align-h="center" class="pay-row">
          <h5>예약날짜</h5>
      </b-row>
      <b-row align-h="center" class="pay-row">
          <div v-if="startDate!==''">{{startDate}} ~ {{endDate}}</div>
      </b-row>
      <b-row align-h="center" class="pay-row">
        <div>
          <b-list-group>
            <b-list-group-item v-for="(pl, i) in purchasingList" :key="i">
              <table>
                <tr>
                  <td>
                    {{pl.name}}
                  </td>
                  <td>
                    {{pl.buyNum}}개
                  </td>
                  <td>
                    {{pl.price * pl.buyNum}}원
                  </td>
                </tr>
              </table>
            </b-list-group-item>
          </b-list-group>
        </div>
      </b-row>
      <b-row align-h="center" class="pay-row">
        <div>
          총 {{totalPrice}}원
        </div>
      </b-row>
      <!-- <b-row align-h="center">
      </b-row> -->
    </b-container>
  </div>
        <b-button @click="pay">카카오페이로 결제</b-button>
        <b-button @click="back">뒤로가기</b-button>
  </div>
</template>

<script>
const API_URL = process.env.VUE_APP_SERVER_URL;

import axios from 'axios';

const now = new Date();
const nowDate = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();

export default {
  name: "Purchase",
  props: {
    pl: {
      type: Array,
    },
    value: {
    },
  },
  data() {
    return {
      isPurchasing: this.value,
      date: nowDate,
      context: null,
      purchasingList: this.pl,
      totalPrice: 0,
      startDate: "",
      endDate: "",
      productName: "",
    }
  },
  created() {
    this.pl.map((p) => {
      this.totalPrice += p.price * p.buyNum;
    });
  },
  methods: {
    back() {
      this.$emit('input', false);
    },
    onContext(ctx) {
      this.context = ctx
    },
    startDateDisabled(ymd) {
      return ymd < nowDate;
    },
    endDateDisabled(ymd) {
      return ymd < this.startDate;
    },
    pay() {
      if(this.startDate===""){
        alert("날짜를 선택해주세요.");
        return;
      }
      
      const products = []; // 상품 아이디
      const nums = [];     // 상품 수량

      this.purchasingList.map((pl) => {
        products.push(pl.id);
        nums.push(pl.num);
      });

      axios({
        method: "POST",
        url: `${API_URL}/reservations`,
            data: {
              "start": this.startDate,
              "end": this.endDate,
              "amount": this.totalPrice,
              "user": this.$cookies.get('uid'),
              "shop": this.purchasingList[0].shop,
              "products": products,
              "nums": nums,
            },
      }).then((res) => {
        this.productName += this.startDate + "~" +this.endDate + " " + this.purchasingList[0].name;
        if(this.purchasingList.length > 1){
          this.productName += " 외 " + (this.purchasingList.length-1);
        }

        axios({
          method: "POST",
            url: `${API_URL}/pay/kakao`,
            data: {
              "amount": this.totalPrice,
              "id": res.data.data,
              "name": this.productName,
              "quantity": 1,
              "shop": this.purchasingList[0].shop,
            }
          }).then((res) => {
            this.$cookies.set("yol_tid", res.data.data.tid);
            window.location.href = res.data.data.next_redirect_pc_url;
          }).catch((err) => {
            console.log(err);
          });
      }).catch((err) => {
        console.log(err);
      });

    }
  },
  watch: {
    startDate (to){
      this.endDate = to;
    },
  },
};
</script>

<style scoped>
.pay-info {
  margin: auto;
  width: 50%;
  background-color:lightgray;
}
.pay-row {
  padding: 3%;
}
</style>
