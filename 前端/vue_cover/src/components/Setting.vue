<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>参数设置</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card" v-loading="loading">
      <el-table
          :data="tableData"
          stripe
          style="width: 100%"
      >
        <el-table-column
            prop="cid"
            label="编号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="name"
            label="名字"
            width="180">
        </el-table-column>
        <el-table-column

            label="标志值" width="180">
          <template v-slot="scope">
            <el-input-number size="small" v-model="scope.row.standard"></el-input-number>
            <!--            <el-button type="danger" icon="el-icon-delete" size="mini" @click="delUser(scope.row.uid)"></el-button>-->
          </template>

        </el-table-column>

        <el-table-column
            prop="unit"
            label="单位"
            width="180">
        </el-table-column>

        <el-table-column label="波动范围">
          <template v-slot="scope">
            <el-input-number v-model="scope.row.rangess"></el-input-number>
            <!--            <el-button type="danger" icon="el-icon-delete" size="mini" @click="delUser(scope.row.uid)"></el-button>-->
          </template>
        </el-table-column>

        <el-table-column

            label="操作"
            width="180">
          <template v-slot="scope">
            <el-button type="primary" icon="el-icon-upload" size="medium" @click="save(scope.row)">保存</el-button>
          </template>
        </el-table-column>

      </el-table>

    </el-card>
  </div>
</template>

<script>
export default {
  name: "Setting",
  data() {
    return {
      tableData: [],
      config: {
        cid: "",
        name: "",
        rangess: "",
        standard: "",
        unit: ""
      },
      loading:true
    }
  },
  methods: {
    getList() {
      var vm = this
      this.$http.get("/config/list").then(function (res) {
        vm.tableData = res.data.data.list
        setTimeout(function () {
          // 这里就是处理的事件
          vm.loading=false
        }, 700);
      })
    },
    save(row) {
     this.config=row
      var vm=this
      this.$http.post("/config/update",this.config).then(function (res){
        vm.$message.success(res.data.message)
      })
      // this.$message.success("保存成功")
    }
  },
  created() {
    this.getList();
  }
}
</script>

<style scoped>

.box-card {
  margin-top: 20px;
  min-height: 800px;

}

</style>
