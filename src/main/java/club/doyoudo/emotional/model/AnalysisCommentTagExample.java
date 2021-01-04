package club.doyoudo.emotional.model;

import java.util.ArrayList;
import java.util.List;

public class AnalysisCommentTagExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnalysisCommentTagExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPhoneIdIsNull() {
            addCriterion("phone_id is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIdIsNotNull() {
            addCriterion("phone_id is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneIdEqualTo(String value) {
            addCriterion("phone_id =", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdNotEqualTo(String value) {
            addCriterion("phone_id <>", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdGreaterThan(String value) {
            addCriterion("phone_id >", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdGreaterThanOrEqualTo(String value) {
            addCriterion("phone_id >=", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdLessThan(String value) {
            addCriterion("phone_id <", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdLessThanOrEqualTo(String value) {
            addCriterion("phone_id <=", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdLike(String value) {
            addCriterion("phone_id like", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdNotLike(String value) {
            addCriterion("phone_id not like", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdIn(List<String> values) {
            addCriterion("phone_id in", values, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdNotIn(List<String> values) {
            addCriterion("phone_id not in", values, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdBetween(String value1, String value2) {
            addCriterion("phone_id between", value1, value2, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdNotBetween(String value1, String value2) {
            addCriterion("phone_id not between", value1, value2, "phoneId");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNull() {
            addCriterion("comment_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNotNull() {
            addCriterion("comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentIdEqualTo(String value) {
            addCriterion("comment_id =", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotEqualTo(String value) {
            addCriterion("comment_id <>", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThan(String value) {
            addCriterion("comment_id >", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThanOrEqualTo(String value) {
            addCriterion("comment_id >=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThan(String value) {
            addCriterion("comment_id <", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThanOrEqualTo(String value) {
            addCriterion("comment_id <=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLike(String value) {
            addCriterion("comment_id like", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotLike(String value) {
            addCriterion("comment_id not like", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdIn(List<String> values) {
            addCriterion("comment_id in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotIn(List<String> values) {
            addCriterion("comment_id not in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdBetween(String value1, String value2) {
            addCriterion("comment_id between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotBetween(String value1, String value2) {
            addCriterion("comment_id not between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andSentimentIsNull() {
            addCriterion("sentiment is null");
            return (Criteria) this;
        }

        public Criteria andSentimentIsNotNull() {
            addCriterion("sentiment is not null");
            return (Criteria) this;
        }

        public Criteria andSentimentEqualTo(Integer value) {
            addCriterion("sentiment =", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentNotEqualTo(Integer value) {
            addCriterion("sentiment <>", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentGreaterThan(Integer value) {
            addCriterion("sentiment >", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentGreaterThanOrEqualTo(Integer value) {
            addCriterion("sentiment >=", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentLessThan(Integer value) {
            addCriterion("sentiment <", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentLessThanOrEqualTo(Integer value) {
            addCriterion("sentiment <=", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentIn(List<Integer> values) {
            addCriterion("sentiment in", values, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentNotIn(List<Integer> values) {
            addCriterion("sentiment not in", values, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentBetween(Integer value1, Integer value2) {
            addCriterion("sentiment between", value1, value2, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentNotBetween(Integer value1, Integer value2) {
            addCriterion("sentiment not between", value1, value2, "sentiment");
            return (Criteria) this;
        }

        public Criteria andAdjIsNull() {
            addCriterion("adj is null");
            return (Criteria) this;
        }

        public Criteria andAdjIsNotNull() {
            addCriterion("adj is not null");
            return (Criteria) this;
        }

        public Criteria andAdjEqualTo(String value) {
            addCriterion("adj =", value, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjNotEqualTo(String value) {
            addCriterion("adj <>", value, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjGreaterThan(String value) {
            addCriterion("adj >", value, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjGreaterThanOrEqualTo(String value) {
            addCriterion("adj >=", value, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjLessThan(String value) {
            addCriterion("adj <", value, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjLessThanOrEqualTo(String value) {
            addCriterion("adj <=", value, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjLike(String value) {
            addCriterion("adj like", value, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjNotLike(String value) {
            addCriterion("adj not like", value, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjIn(List<String> values) {
            addCriterion("adj in", values, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjNotIn(List<String> values) {
            addCriterion("adj not in", values, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjBetween(String value1, String value2) {
            addCriterion("adj between", value1, value2, "adj");
            return (Criteria) this;
        }

        public Criteria andAdjNotBetween(String value1, String value2) {
            addCriterion("adj not between", value1, value2, "adj");
            return (Criteria) this;
        }

        public Criteria andPropIsNull() {
            addCriterion("prop is null");
            return (Criteria) this;
        }

        public Criteria andPropIsNotNull() {
            addCriterion("prop is not null");
            return (Criteria) this;
        }

        public Criteria andPropEqualTo(String value) {
            addCriterion("prop =", value, "prop");
            return (Criteria) this;
        }

        public Criteria andPropNotEqualTo(String value) {
            addCriterion("prop <>", value, "prop");
            return (Criteria) this;
        }

        public Criteria andPropGreaterThan(String value) {
            addCriterion("prop >", value, "prop");
            return (Criteria) this;
        }

        public Criteria andPropGreaterThanOrEqualTo(String value) {
            addCriterion("prop >=", value, "prop");
            return (Criteria) this;
        }

        public Criteria andPropLessThan(String value) {
            addCriterion("prop <", value, "prop");
            return (Criteria) this;
        }

        public Criteria andPropLessThanOrEqualTo(String value) {
            addCriterion("prop <=", value, "prop");
            return (Criteria) this;
        }

        public Criteria andPropLike(String value) {
            addCriterion("prop like", value, "prop");
            return (Criteria) this;
        }

        public Criteria andPropNotLike(String value) {
            addCriterion("prop not like", value, "prop");
            return (Criteria) this;
        }

        public Criteria andPropIn(List<String> values) {
            addCriterion("prop in", values, "prop");
            return (Criteria) this;
        }

        public Criteria andPropNotIn(List<String> values) {
            addCriterion("prop not in", values, "prop");
            return (Criteria) this;
        }

        public Criteria andPropBetween(String value1, String value2) {
            addCriterion("prop between", value1, value2, "prop");
            return (Criteria) this;
        }

        public Criteria andPropNotBetween(String value1, String value2) {
            addCriterion("prop not between", value1, value2, "prop");
            return (Criteria) this;
        }

        public Criteria andAbstractTextIsNull() {
            addCriterion("abstract_text is null");
            return (Criteria) this;
        }

        public Criteria andAbstractTextIsNotNull() {
            addCriterion("abstract_text is not null");
            return (Criteria) this;
        }

        public Criteria andAbstractTextEqualTo(String value) {
            addCriterion("abstract_text =", value, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextNotEqualTo(String value) {
            addCriterion("abstract_text <>", value, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextGreaterThan(String value) {
            addCriterion("abstract_text >", value, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextGreaterThanOrEqualTo(String value) {
            addCriterion("abstract_text >=", value, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextLessThan(String value) {
            addCriterion("abstract_text <", value, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextLessThanOrEqualTo(String value) {
            addCriterion("abstract_text <=", value, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextLike(String value) {
            addCriterion("abstract_text like", value, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextNotLike(String value) {
            addCriterion("abstract_text not like", value, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextIn(List<String> values) {
            addCriterion("abstract_text in", values, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextNotIn(List<String> values) {
            addCriterion("abstract_text not in", values, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextBetween(String value1, String value2) {
            addCriterion("abstract_text between", value1, value2, "abstractText");
            return (Criteria) this;
        }

        public Criteria andAbstractTextNotBetween(String value1, String value2) {
            addCriterion("abstract_text not between", value1, value2, "abstractText");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}