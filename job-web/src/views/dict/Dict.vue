<template>
    <div style="background: #f2f2f2">
        <el-row :gutter="20">
            <el-col :span="5">
                <el-card>
                    <ul class="type">
                        <li @click="handleType(index,item.id)" :class="active==index?'active':''"
                            v-for="(item,index) in typeTableData" :key="index">
                            {{item.name}}
                        </li>
                    </ul>
                </el-card>
            </el-col>
            <el-col :span="19">
                <el-card>
                    <div class="btn-panel">
                        <el-button size="small" type="primary" icon="el-icon-plus" @click="create">新增</el-button>
                        <el-button size="small" type="primary" icon="el-icon-edit" @click="update">修改</el-button>
                        <el-button size="small" type="danger" icon="el-icon-delete" @click="del">删除</el-button>
                    </div>
                    <r-table ref="mutipleTable" :table-data="dictTableData" :table-cols="dictTableCols">
                        <template slot="slot_status" slot-scope="scope">
                            <el-tag v-if="scope.data.status==0" type="success" size="mini">正常</el-tag>
                            <el-tag v-else type="danger" size="mini">停用</el-tag>
                        </template>
                    </r-table>
                </el-card>
                <Add v-if="add.visible" :param="add" :typeId="selectTypeId"></Add>
                <Edit v-if="edit.visible" :param="edit" :typeId="selectTypeId"></Edit>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import {type_query, dict_query, del} from '../../api/dict';
    import RTable from "../../components/RTable";
    import Add from "./Add";
    import Edit from "./Edit";

    export default {
        name: "Dict",
        components: {Edit, Add, RTable},
        data() {
            return {
                typeTableData: [],
                active: 0,
                selectTypeId: 0,
                typeTableCols: [
                    {prop: 'name', label: '类型名称'}
                ],
                dictTableData: [],
                dictTableCols: [
                    {prop: 'dictKey', label: '字典名称'},
                    {prop: 'dictVal', label: '字典值'},
                    {prop: 'status', label: '状态', slot: 'slot_status'},
                    {prop: 'sort', label: '序号', width: 100},
                ],
                add: {
                    visible: false,
                    close: this.close,
                    callback: this.reload
                },
                edit: {
                    visible: false,
                    close: this.close,
                    callback: this.reload,
                    form: null
                }
            }
        },
        mounted() {
            type_query({}).then(res => {
                this.typeTableData = res.data;
                this.selectTypeId = this.typeTableData[0].id;
            })
            this.list({typeId: this.selectTypeId})
        },
        methods: {
            create() {
                this.add.visible = true;
            },
            update() {
                let selections = this.$refs['mutipleTable'].selection;
                if (selections.length == 1) {
                    this.edit.visible = true;
                    this.edit.form = selections[0];
                } else {
                    this.$message.warning("请选择一条数据进行修改");
                }
            },
            reload() {
                this.list({typeId: this.selectTypeId})
            },
            list(param) {
                dict_query(param).then(res => {
                    this.dictTableData = res.data;
                })
            },
            close() {
                this.add.visible = false;
                this.edit.visible = false;
            },
            del() {
                let selections = this.$refs['mutipleTable'].selection;
                if (selections.length > 0) {
                    this.$confirm("确定有删除吗？", "删除提示").then(() => {
                        let arr = selections.map(item => item.id);
                        let ids = arr.join(',');
                        del(ids).then(res => {
                            this.$message.success(res.msg);
                            this.reload();
                        }).catch(() => {
                        })
                    })
                } else {
                    this.$message.warning("请选择要删除的数据");
                }
            },
            handleType(index, typeId) {
                this.active = index;
                this.selectTypeId = typeId;
                this.list({typeId: typeId})
            }
        }
    }
</script>

<style scoped>
    .type {
        list-style: none;
        margin: 0;
        padding: 0;
    }

    .type li {
        height: 40px;
        padding-left: 20px;
        line-height: 40px;
        box-sizing: border-box;
        cursor: pointer;
        font-size: 14px;
    }

    .type .active {
        background-color: #009399;
        color: #ffffff;
        border-radius: 10px;
    }
</style>