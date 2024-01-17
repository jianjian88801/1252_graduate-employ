import request from '@/utils/request';

export function queryMenus() {
    return request({
        url: 'menu/query',
        method: 'post'
    })
}