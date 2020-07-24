package com.wicresoft.ghealth.common.dto;

import org.hibernate.validator.constraints.NotBlank;

public class SetMealInfo {
    private Long setMealId;

    private String customerId;
    
    @NotBlank(message = "setMeal.breakfast.isempty")
    private String breakfast;

    @NotBlank(message = "setMeal.lunch.isempty")
    private String lunch;

    @NotBlank(message = "setMeal.dinner.isempty")
    private String dinner;

    private String memo;

    public Long getSetMealId() {
        return setMealId;
    }

    public void setSetMealId(Long setMealId) {
        this.setMealId = setMealId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

	/**
	 * @return the breakfast
	 */
	public String getBreakfast() {
		return breakfast;
	}

	/**
	 * @param breakfast the breakfast to set
	 */
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}

	/**
	 * @return the lunch
	 */
	public String getLunch() {
		return lunch;
	}

	/**
	 * @param lunch the lunch to set
	 */
	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

	/**
	 * @return the dinner
	 */
	public String getDinner() {
		return dinner;
	}

	/**
	 * @param dinner the dinner to set
	 */
	public void setDinner(String dinner) {
		this.dinner = dinner;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
}