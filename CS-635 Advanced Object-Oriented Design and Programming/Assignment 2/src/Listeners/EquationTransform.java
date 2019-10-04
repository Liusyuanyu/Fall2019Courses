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
        String cleanValue=value.replace(" ","");
        if(valueOnlyEquation.length()==0)
        {
            valueOnlyEquation.append(cleanValue);
        }
        else
        {
            valueOnlyEquation.append(" ");
            valueOnlyEquation.append(cleanValue);
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
    public String getValueOnlyEquation()
    {
        return valueOnlyEquation.toString();
    }
}
