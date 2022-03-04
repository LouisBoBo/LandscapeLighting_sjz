package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.List;


public class UserData implements Serializable {

    /**
     * code : 200
     * data : {"createTime":1532003678000,"email":"13988888888@163.com","gender":0,"id":1,"name":"超级管理员","partition":{"addr":"硚口区","city":"武汉市","description":"硚口区","district":"硚口区","energyCharge":1.6,"id":1,"name":"硚口区","province":"湖北省"},"password":"dee71d3de36562ee77d5cfb1354e958b","phone":"15988888888","pid":1,"roleIds":null,"roles":[{"createTime":null,"grade":1,"id":1,"name":"超级管理员","partitionId":null,"permissions":null,"type":null,"updateTime":null}],"status":1,"type":1,"updateTime":1550760413000}
     * message : SUCCESS
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean implements Serializable {
        /**
         * createTime : 1532003678000
         * email : 13988888888@163.com
         * gender : 0
         * id : 1
         * name : 超级管理员
         * partition : {"addr":"硚口区","city":"武汉市","description":"硚口区","district":"硚口区","energyCharge":1.6,"id":1,"name":"硚口区","province":"湖北省"}
         * password : dee71d3de36562ee77d5cfb1354e958b
         * phone : 15988888888
         * pid : 1
         * roleIds : null
         * roles : [{"createTime":null,"grade":1,"id":1,"name":"超级管理员","partitionId":null,"permissions":null,"type":null,"updateTime":null}]
         * status : 1
         * type : 1
         * updateTime : 1550760413000
         */

        private long createTime;
        private String email;
        private int gender;
        private int id;
        private String name;
        private PartitionBean partition;
        private String password;
        private String phone;
        private int pid;
        private Object roleIds;
        private int status;
        private int type;
        private long updateTime;
        private List<RolesBean> roles;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PartitionBean getPartition() {
            return partition;
        }

        public void setPartition(PartitionBean partition) {
            this.partition = partition;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds = roleIds;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public List<RolesBean> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesBean> roles) {
            this.roles = roles;
        }

        public static class PartitionBean implements Serializable {
            /**
             * addr : 硚口区
             * city : 武汉市
             * description : 硚口区
             * district : 硚口区
             * energyCharge : 1.6
             * id : 1
             * name : 硚口区
             * province : 湖北省
             */

            private String addr;
            private String city;
            private String description;
            private String district;
            private double energyCharge;
            private int id;
            private String name;
            private String province;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public double getEnergyCharge() {
                return energyCharge;
            }

            public void setEnergyCharge(double energyCharge) {
                this.energyCharge = energyCharge;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }
        }

        public static class RolesBean implements Serializable {
            /**
             * createTime : null
             * grade : 1
             * id : 1
             * name : 超级管理员
             * partitionId : null
             * permissions : null
             * type : null
             * updateTime : null
             */

            private Object createTime;
            private int grade;
            private int id;
            private String name;
            private Object partitionId;
            private Object permissions;
            private Object type;
            private Object updateTime;

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getPartitionId() {
                return partitionId;
            }

            public void setPartitionId(Object partitionId) {
                this.partitionId = partitionId;
            }

            public Object getPermissions() {
                return permissions;
            }

            public void setPermissions(Object permissions) {
                this.permissions = permissions;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
