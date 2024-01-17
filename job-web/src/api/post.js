import request from '@/utils/request';

export function create(data) {
    return request({
        url: 'post/create',
        method: 'post',
        data: data
    })
}

export function update(data) {
    return request({
        url: 'post/update',
        method: 'post',
        data: data
    })
}

export function del(ids) {
    return request({
        url: 'post/delete',
        method: 'post',
        params: {"ids": ids}
    })
}

export function detail(id) {
    return request({
        url: 'post/detail',
        method: 'post',
        params: {"id": id}
    })
}

export function query(data) {
    return request({
        url: 'post/query',
        method: 'post',
        data: data
    })
}