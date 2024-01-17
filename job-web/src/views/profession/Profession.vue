<template>
    <div>
        <div class="btn-panel">
            <el-button size="small" type="primary" icon="el-icon-plus" @click="create">新增</el-button>
            <el-button size="small" type="primary" icon="el-icon-edit" @click="update">修改</el-button>
            <el-button size="small" type="danger" icon="el-icon-delete" @click="del">删除</el-button>
        </div>
        <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
        <Add v-if="add.visible" :param="add"></Add>
        <Edit v-if="edit.visible" :param="edit"></Edit>
    </div>
</template>

<script>
    import {tree, del} from '@/api/profession'
    import Add from "./Add";
    import Edit from "./Edit";
    import {message} from "../../utils/message";

    export default {
        components: {Edit, Add},
        data() {
            return {
                data: [],
                selectNode: null,
                defaultProps: {
                    children: 'children',
                    label: 'label',
                    parentId: 'parentId',
                    id: 'id'
                },
                add: {
                    visible: false,
                    close: this.close,
                    callback: this.tree
                },
                edit: {
                    visible: false,
                    close: this.close,
                    callback: this.tree,
                    form: {}
                }
            };
        },
        mounted() {
            this.tree();
        },
        methods: {
            handleNodeClick(data) {
                this.edit.form = data;
                this.selectNode = data;
            },
            tree() {
                tree().then(res => {
                    this.data = res.data;
                })
            },
            create() {
                this.add.visible = true;
            },
            update() {
                this.edit.visible = true;
            },
            del() {
                if (this.selectNode) {
                    this.$confirm("确定有删除吗？", "删除提示").then(() => {
                        del(this.selectNode.id).then(res => {
                            this.$message.success(res.msg);
                            this.tree();
                        })
                    }).catch(() => {
                    })
                } else {
                    message.warning('请选择要删除的节点');
                    return;
                }
            },
            close() {
                this.add.visible = false;
                this.edit.visible = false;
            }
        }
    };
</script>

<style scoped>

</style>