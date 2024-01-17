import request from '@/utils/request';

export function type_query(data) {
    return request({
        url: 'type/query',
        method: 'post',
        data: data
    })
}

export function create(data) {
    return request({
        url: 'dict/create',
        method: 'post',
        data: data
    })
}

export function update(data) {
    return request({
        url: 'dict/update',
        method: 'post',
        data: data
    })
}

export function del(ids) {
    return request({
        url: 'dict/delete',
        method: 'post',
        params: {"ids": ids}
    })
}

export function detail(id) {
    return request({
        url: 'dict/detail',
        method: 'post',
        params: {"id": id}
    })
}

export function dict_query(data) {
    return request({
        url: 'dict/query',
        method: 'post',
        data: data
    })
}