<template>

    <el-card >
      <div class="one">


          <e-charts class="chart" :option="option"></e-charts>

          <div class="right_wd">

             <div id="mychart1"></div>
             <div class="watch">实时水温</div>
             <div><el-descriptions class="margin-top" title="详细数据" :column="1"  size="" border>
              <template slot="extra">
                <el-button type="primary" size="small" @click="refresh">刷新</el-button>
                <el-button type="danger" size="small" @click="delBtn">删除</el-button>
              </template>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-user"></i>
                  井盖编号
                </template>
              <el-tag>{{cover.cid}}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-mobile-phone"></i>
                  水位
                </template>
                {{cover.waterLevel}}m
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  倾斜角度
                </template>
                {{cover.inclination}}度
              </el-descriptions-item>

              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-tickets"></i>
                  地理坐标
                </template>
                <el-tag size="small" type="danger">东经{{cover.gpsEw}}</el-tag>
                <el-tag size="small">北纬{{cover.gpsNs}}</el-tag>
              </el-descriptions-item>

              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-tickets"></i>
                  负责人
                </template>
                {{cover.man}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-office-building"></i>
                  所属地区
                </template>
                {{cover.address}}
              </el-descriptions-item>
            </el-descriptions></div>
          </div>

      </div>

      <el-dialog
          title="提示"
          :visible.sync="dialogVisible"
          width="30%"
         >
        <span>是否删除该井盖</span>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="deleteCover" >确 定</el-button>
  </span>
      </el-dialog>

    </el-card>

</template>

<script>
import * as echarts from "echarts";

export default {
  name: "CoverOne",


  data() {
    return {
      cover:{
        cid:"",
        waterLevel:"",
        h2s: "",
        ch4: "",
        inclination: "",
        temperature: "",
        gpsEw: "",
        gpsNs: "",
        man: "",
        address: ""
      },
      dialogVisible:false
    };
  },
  mounted() {
    this.initEcharts();

  },
  methods: {

    initEcharts() {
      setInterval(function () {
        const random = +(Math.random()*(21-19+1)+19).toFixed(2);
        myChart1.setOption({
          series: [
            {
              data: [
                {
                  value: random
                }
              ]
            },
            {
              data: [
                {
                  value: random
                }
              ]
            }
          ]
        });
      }, 2000);
      const option1= {
        series: [
          {
            type: 'gauge',
            center: ['50%', '60%'],
            startAngle: 200,
            endAngle: -20,
            min: 0,
            max: 60,
            splitNumber: 12,
            itemStyle: {
              color: '#FFAB91'
            },
            progress: {
              show: true,
              width: 30
            },
            pointer: {
              show: false
            },
            axisLine: {
              lineStyle: {
                width: 30
              }
            },
            axisTick: {
              distance: -45,
              splitNumber: 5,
              lineStyle: {
                width: 2,
                color: '#999'
              }
            },
            splitLine: {
              distance: -52,
              length: 14,
              lineStyle: {
                width: 3,
                color: '#999'
              }
            },
            axisLabel: {
              distance: -20,
              color: '#999',
              fontSize: 20
            },
            anchor: {
              show: false
            },
            title: {
              show: false
            },
            detail: {
              valueAnimation: true,
              width: '60%',
              lineHeight: 40,
              borderRadius: 8,
              offsetCenter: [0, '-15%'],
              fontSize: 60,
              fontWeight: 'bolder',
              formatter: '{value} °C',
              color: 'inherit'
            },
            data: [
              {
                value: 20.01
              }
            ]
          },
          {
            type: 'gauge',
            center: ['50%', '60%'],
            startAngle: 200,
            endAngle: -20,
            min: 0,
            max: 60,
            itemStyle: {
              color: '#FD7347'
            },
            progress: {
              show: true,
              width: 8
            },
            pointer: {
              show: false
            },
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            detail: {
              show: false
            },
            data: [
              {
                value: 20.99
              }
            ]
          }
        ]
      };
      const myChart1= echarts.init(document.getElementById("mychart1"));
      myChart1.setOption(option1);
      //随着屏幕大小调节图表
      // window.addEventListener("resize", () => {
        myChart1.resize();
      // });
    },


    //查询当前井盖信息
    getOneByid(){
      const id=this.$route.params.id
      var vm=this
      this.$http.get("/cover/"+id).then(function (res){
         vm.cover=res.data.data.one
      })
    },
    //刷新数据
    refresh(){
      this.getOneByid();
      this.$message.success("刷新数据成功")
    },
    //确认删除按钮
    delBtn(){
      this.dialogVisible=true
    },
    //删除
    deleteCover(){
      this.dialogVisible=false
      var vm=this;

      this.$http.delete("/cover/"+vm.cover.cid).then(function (res){
          if(res.data.code===20000){
            vm.$router.push("/list")
            vm.$message.success("删除成功！！！")
          }else {
            vm.$message.error("删除失败！！！")
          }
      })
    }

  },
  created() {
    // this.$message.success(this.$route.params.id)
    // this.$route.params.id
    this.getOneByid();
  },
  computed:{
    option(){
      return {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: '井内气体分布',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              {value: this.cover.ch4, name: '甲烷'},
              {value: this.cover.h2s, name: '硫化氢'},
              {value: (Math.random()*(70-30+1)+30).toFixed(2), name: '氧气'},
              {value: (Math.random()*(70-30+1)+30).toFixed(2), name: '二氧化碳'},
              {value: (Math.random()*(70-30+1)+30).toFixed(2), name: '其他气体'},
            ]
          }
        ]
      }
    }
  }


}
</script>

<style scoped>

.one{
  height: 810px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.chart{
  height: 100%;
  /*background-color: #409eff;*/
}
.margin-top{
  width: 700px;
}
.right_wd{
  display: flex;
  flex-direction: column;
  align-items: center;
  /*justify-content: center;*/
}
#mychart1{
  width: 700px;
  height: 700px;
  /*background-color: yellow;*/
}
.watch{
  color:black;
  font-weight: bold;
  font-size: 18px;
  text-align: center;
}

</style>
