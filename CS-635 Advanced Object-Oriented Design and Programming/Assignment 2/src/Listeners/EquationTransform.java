package Listeners;

public class EquationTransform
{
    private StringBuilder valueOnlyEquation;
    private Boolean isCorrect;

    public EquationTransform()
    {
        valueOnlyEquation = new StringBuilder();
        isCorrect = true;
    }
    public void append(String value)
    {
        String cleanVlaue=value.replace(" ","");
        if(valueOnlyEquation.capacity()==0)
        {
            valueOnlyEquation.append(cleanVlaue);
        }
        else
        {
            valueOnlyEquation.append(" ");
            valueOnlyEquation.append(cleanVlaue);
        }
    }
    public void setIsCorrect(Boolean value)
    {
        isCorrect = value;
    }
    public Boolean getIsCorrect()
    {
        return isCorrect;
    }
    public String getvalueOnlyEquation()
    {
        return valueOnlyEquation.toString();
    }
}
