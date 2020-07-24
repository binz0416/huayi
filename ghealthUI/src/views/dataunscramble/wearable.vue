<template>
<div class="tab-pane" id="tab_5">
  <!-- <b>{{wearableInfo}}</b> -->
  <div id='canvas' width="100%">
    <h1>心电图</h1><br/>
    <canvas id="bgtable" height="226px" style="position: absolute;"></canvas>
    <canvas id="ecg" height="226px" style="position: relative;"></canvas>
    <div style="height:15px"></div>
  </div>
  <div class="col-xs12">
    <div class="box box-primary">
      <table width="100%">
        <tr>
          <td colspan="2" class="tbTitle"> {{$t("labels.baseInfo")}}</td>
        </tr>
        <tr>
          <td class="tdText"><strong>{{$t("labels.avgHeart")}}</strong></td>
          <td class="tdValue"><strong>{{wearableInfo.avgHeart}}</strong></td>
        </tr>
        <tr>
          <td class="tdText"><strong>{{$t("labels.path")}}</strong></td>
          <td class="tdValue"><strong>{{wearableInfo.path}}</strong></td>
        </tr>
        <tr>
          <td class="tdText"><strong>{{$t("labels.beatNum")}}</strong></td>
          <td class="tdValue"><strong>{{wearableInfo.beatNum}}</strong></td>
        </tr>
        <tr>
          <td class="tdText"><strong>{{$t("labels.maxHeart")}}</strong></td>
          <td class="tdValue"><strong>{{wearableInfo.maxHeart}}</strong></td>
        </tr>
        <tr>
          <td class="tdText"><strong>{{$t("labels.minHeart")}}</strong></td>
          <td class="tdValue"><strong>{{wearableInfo.minHeart}}</strong></td>
        </tr>
      </table>
    </div>
    <div class="box box-primary">
      <table width="100%">
        <tr>
          <td colspan="2" class="tbTitle"> {{$t("labels.unnormalItem")}}</td>
        </tr>
        <tr>
          <td class="tdText"><strong>{{$t("labels.tachNum")}}</strong></td>
          <td class="tdValue"><strong>{{wearableInfo.tachNum}}</strong></td>
        </tr>
        <tr>
          <td class="tdText"><strong>{{$t("labels.braNum")}}</strong></td>
          <td class="tdValue"><strong>{{wearableInfo.braNum}}</strong></td>
        </tr>

      </table>
    </div>

  </div>
  <table class="TBclass">
    <tr>
      <td style="width:100%">
        <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%;margin:5px 0px 5px 0px" />
      </td>
    </tr>
  </table>
  <div>
    <div style="color:red; ">{{$t("Titles.doctorTips")}}</div>
    <div id="suggestforWear">
      <textarea style="height:100px;width:98%;margin:3px" v-model="wearableInfo.detailAdvise"></textarea>
    </div>
  </div>
</div>
</template>

<script>
var index = 0,
  lineX = 0,
  lineY = 0,
  linepan,
  can,
  bgtable;
var dataY = "";
var dataYSize = 0.5;
var canvasWidth = 0;
export default {
  data() {
    return {
      screenWidth: 0,
      timer: false
    }
  },
  props: ['wearableInfo', 'ecgData'],
  mounted() {
    this.screenWidth = $('#tab_1').width()
    const that = this;
    window.onresize = () => {
      return (() => {
        that.screenWidth = $('#canvas').width()
      })()
    }
    this.setWidth();
    this.init()
  },
  beforeDestroy() {
    this.clearLine();
  },
  methods: {
    drawSmallGrid() {
      var context = bgtable.getContext("2d");
      context.strokeStyle = "#f1dedf";
      context.strokeWidth = 1;
      context.beginPath();
      for (var x = 0.5; x < canvasWidth; x += 3) {
        context.moveTo(x, 0);
        context.lineTo(x, 226 * 3);
        context.stroke();
      }
      for (var y = 0.5; y < 226 * 3; y += 3) {
        context.moveTo(0, y);
        context.lineTo(canvasWidth, y);
        context.stroke();
      }
      context.closePath();
      return;
    },

    drawMediumGrid() {
      var context = bgtable.getContext("2d");
      context.strokeStyle = "#f0adaa";
      context.strokeWidth = 1;
      context.beginPath();
      for (var x = 0.5; x < canvasWidth; x += 15) {
        context.moveTo(x, 0);
        context.lineTo(x, 226 * 3);
        context.stroke();
      };


      for (var y = 0.5; y < 226 * 3; y += 15) {
        context.moveTo(0, y);
        context.lineTo(canvasWidth, y);
        context.stroke();
      };
      context.closePath();
      return;
    },

    drawBigGrid() {
      var context = bgtable.getContext("2d");
      context.strokeStyle = "#e0514b";
      context.strokeWidth = 1;
      context.beginPath();
      for (var x = 0.5; x < canvasWidth; x += 75) {
        context.moveTo(x, 0);
        context.lineTo(x, 226 * 3);
        context.stroke();
      };

      for (var y = 0.5; y < 226 * 3; y += 75) {
        context.moveTo(0, y);
        context.lineTo(canvasWidth, y);
        context.stroke();
      };
      context.closePath();
      return;
    },

    init() {
      can = document.getElementById("ecg");
      bgtable = document.getElementById("bgtable");
      can.width = canvasWidth;
      bgtable.width = canvasWidth;
      this.drawSmallGrid();
      this.drawMediumGrid();
      this.drawBigGrid();
    },
    setWidth() {
      var aa = this.screenWidth
      var big = parseInt(aa / 75)
      canvasWidth = big * 75 + 1
    },
    drawLine() {
      linepan = can.getContext('2d');
      linepan.restore();
      linepan.strokeStyle = "#000";
      linepan.strokeWidth = 1;
      linepan.beginPath();
      linepan.moveTo(0, dataY[lineY]);
      index = setInterval(() => {
        this.moveLine()
      }, 0.5)
    },

    moveLine() {
      lineX++;
      lineY++;
      if (lineY === dataY.length) {
        lineY = 1;
      }
      if (lineX >= canvasWidth * 5) {
        linepan.closePath();
        clearInterval(index);
        index = 0;
        lineX = 0;
        this.drawLine();
      }
      let drawX = lineX * 0.2;
      let drawY = 520 - parseInt(dataY[lineY] / dataYSize);
      linepan.clearRect(drawX, 0, 5, 226 * 3);
      linepan.lineTo(drawX, drawY);
      linepan.stroke();
      linepan.restore();
    },
    clearLine() {
      clearInterval(index);
      index = 0;
      lineX = 0;
      can.height = can.height
    }
  },
  watch: {
    'ecgData': function(n, o) {
      dataY = n;
      if (dataY !== "") {
        this.drawLine();
      } else {
        this.clearLine();
      }
    },
    screenWidth(val) {
      this.screenWidth = val
      if (!this.timer) {
        index = 0;
        lineX = 0;
        lineY = 0;
        this.timer = true
        let that = this
        setTimeout(function() {
          that.setWidth();
          that.init()
          that.clearLine();
          if (dataY != "") {
            that.drawLine();
          }
          that.timer = false
        }, 300);
      }
    }
  }
}
</script>

<style scoped>
.TBclass {
  width: 100%;
  text-align: center;
}
.tdText {
  text-align: left;
  width: 40%;
  padding: 3px 0px 3px 25px;
  border: 1px solid #3C8DBC;
}

.tdValue {
  text-align: left;
  padding: 3px 0px 3px 25px;
  border: 1px solid #3C8DBC;
}

.tbTitle {
  text-align: left;
  font-weight: bold;
  padding: 1px 1px 1px 10px;
  color: white;
  font-size: 14px;
  background-color: #3C8DBC;

}
</style>
