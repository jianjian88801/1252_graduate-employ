import request from '@/utils/request';

export function tree() {
    return request({
        url: 'profession/tree',
        method: 'post'
    })
}

export function query(data) {
    return request({
        url: 'profession/query',
        method: 'post',
        data: data
    })
}

export function create(data) {
    return request({
        url: 'profession/create',
        method: 'post',
        data: data
    })
}

export function update(data) {
    return request({
        url: 'profession/update',
        method: 'post',
        data: data
    })
}

export function del(ids) {
    return request({
        url: 'profession/delete',
        method: 'post',
        params: {"ids": ids}
    })
}