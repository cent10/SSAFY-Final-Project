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
          <b-form-datepicker hide-header id="start-date" v-model="startDate"></b-form-datepicker>
        </div>
        <div>
          <label for="end-date">끝 날짜</label>
          <b-form-datepicker :disabled="startDate === ''" :date-disabled-fn="dateDisabled" hide-header id="end-date" v-model="endDate"></b-form-datepicker>
        </div>
      </b-row>
      <b-row align-h="center" class="pay-row">
          <h5>예약날짜</h5>
          {{startDate}} ~ {{endDate}}
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
        <b-button>카카오페이로 결제</b-button>
        <b-button @click="back">뒤로가기</b-button>
  </div>
</template>

<script>

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
    const now = new Date();
    return {
      isPurchasing: this.value,
      date: now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate(),
      context: null,
      purchasingList: this.pl,
      totalPrice: 0,
      startDate: "",
      endDate: "",
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
    dateDisabled(ymd) {
      return ymd < this.startDate;
    }
  },
  watch: {
    startDate (to){
      this.endDate = to;
    },
  }
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
