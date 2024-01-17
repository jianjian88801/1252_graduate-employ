export const studentRouterMap = [
    {
        path: '/index',
        // redirect:'/student_info',
        component: () => import('@/views/Index'),
        children: [
            {
                path: '/student_info',
                name: 'student_info',
                component: () => import('@/views/student/StudentInfo'),
            },
            {
                path: '/resume',
                name: 'resume',
                component: () => import('@/views/resume/Resume'),
            },
            {
                path: '/intention',
                name: 'intention',
                component: () => import('@/views/intention/Intention'),
            },
            {
                path: '/train',
                name: 'train',
                component: () => import('@/views/train/Train'),
            },
            {
                path: '/certificate',
                name: 'certificate',
                component: () => import('@/views/certificate/Certificate'),
            },
            {
                path: '/project',
                name: 'project',
                component: () => import('@/views/project/Project'),
            },
            {
                path: '/experience',
                name: 'experience',
                component: () => import('@/views/experience/Experience'),
            },
            {
                path: '/favor',
                name: 'favor',
                component: () => import('@/views/favor/Favor'),
            },
            {
                path: '/student_send',
                name: 'student_send',
                component: () => import('@/views/send/StudentSend'),
            },
        ]
    }
]
