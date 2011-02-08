#ifndef __X10_ARRAY_ARRAY__VALUESEQUENCE_H
#define __X10_ARRAY_ARRAY__VALUESEQUENCE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_SEQUENCE_H_NODEPS
#include <x10/lang/Sequence.h>
#undef X10_LANG_SEQUENCE_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(U)> class Array__ValueIterator;
} } 
namespace x10 { namespace array { 
template<class FMGL(U)> class Array__ValueIterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace array { 

template<class FMGL(T)> class Array__ValueSequence;
template <> class Array__ValueSequence<void>;
template<class FMGL(T)> class Array__ValueSequence : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(size);
    
    static x10aux::itable_entry _itables[5];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::lang::Sequence<FMGL(T)>::template itable<x10::array::Array__ValueSequence<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::array::Array__ValueSequence<FMGL(T)> > _itable_1;
    
    static typename x10::lang::Fun_0_1<x10_int, FMGL(T)>::template itable<x10::array::Array__ValueSequence<FMGL(T)> > _itable_2;
    
    static typename x10::lang::Iterable<FMGL(T)>::template itable<x10::array::Array__ValueSequence<FMGL(T)> > _itable_3;
    
    void _instance_init();
    
    x10aux::ref<x10::array::Array<FMGL(T)> > FMGL(out__);
    
    x10aux::ref<x10::array::Array<FMGL(T)> > FMGL(array);
    
    virtual x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator();
    void _constructor(x10aux::ref<x10::array::Array<FMGL(T)> > out__);
    
    static x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> > _make(
             x10aux::ref<x10::array::Array<FMGL(T)> > out__);
    
    virtual FMGL(T) __apply(x10_int i);
    x10_int size();
    virtual x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> > x10__array__Array__ValueSequence____x10__array__Array__ValueSequence__this(
      );
    virtual x10aux::ref<x10::array::Array<FMGL(T)> > x10__array__Array__ValueSequence____x10__array__Array__this(
      );
    void __fieldInitializers1178();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class Array__ValueSequence<void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_ARRAY_ARRAY__VALUESEQUENCE_H

namespace x10 { namespace array { 
template<class FMGL(T)>
class Array__ValueSequence;
} } 

#ifndef X10_ARRAY_ARRAY__VALUESEQUENCE_H_NODEPS
#define X10_ARRAY_ARRAY__VALUESEQUENCE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Sequence.h>
#include <x10/lang/Int.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/array/Array__ValueIterator.h>
#include <x10/array/Array__ValueIterator.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/lang/ClassCastException.h>
#ifndef X10_ARRAY_ARRAY__VALUESEQUENCE_H_GENERICS
#define X10_ARRAY_ARRAY__VALUESEQUENCE_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::array::Array__ValueSequence<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::array::Array__ValueSequence<FMGL(T)> >(), 0, sizeof(x10::array::Array__ValueSequence<FMGL(T)>))) x10::array::Array__ValueSequence<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_ARRAY__VALUESEQUENCE_H_GENERICS
#ifndef X10_ARRAY_ARRAY__VALUESEQUENCE_H_IMPLEMENTATION
#define X10_ARRAY_ARRAY__VALUESEQUENCE_H_IMPLEMENTATION
#include <x10/array/Array__ValueSequence.h>



//#line 351 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.PropertyDecl_c
template<class FMGL(T)> typename x10::lang::Sequence<FMGL(T)>::template itable<x10::array::Array__ValueSequence<FMGL(T)> >  x10::array::Array__ValueSequence<FMGL(T)>::_itable_0(&x10::array::Array__ValueSequence<FMGL(T)>::equals, &x10::array::Array__ValueSequence<FMGL(T)>::hashCode, &x10::array::Array__ValueSequence<FMGL(T)>::iterator, &x10::array::Array__ValueSequence<FMGL(T)>::__apply, &x10::array::Array__ValueSequence<FMGL(T)>::size, &x10::array::Array__ValueSequence<FMGL(T)>::toString, &x10::array::Array__ValueSequence<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::array::Array__ValueSequence<FMGL(T)> >  x10::array::Array__ValueSequence<FMGL(T)>::_itable_1(&x10::array::Array__ValueSequence<FMGL(T)>::equals, &x10::array::Array__ValueSequence<FMGL(T)>::hashCode, &x10::array::Array__ValueSequence<FMGL(T)>::toString, &x10::array::Array__ValueSequence<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::lang::Fun_0_1<x10_int, FMGL(T)>::template itable<x10::array::Array__ValueSequence<FMGL(T)> >  x10::array::Array__ValueSequence<FMGL(T)>::_itable_2(&x10::array::Array__ValueSequence<FMGL(T)>::equals, &x10::array::Array__ValueSequence<FMGL(T)>::hashCode, &x10::array::Array__ValueSequence<FMGL(T)>::__apply, &x10::array::Array__ValueSequence<FMGL(T)>::toString, &x10::array::Array__ValueSequence<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::lang::Iterable<FMGL(T)>::template itable<x10::array::Array__ValueSequence<FMGL(T)> >  x10::array::Array__ValueSequence<FMGL(T)>::_itable_3(&x10::array::Array__ValueSequence<FMGL(T)>::equals, &x10::array::Array__ValueSequence<FMGL(T)>::hashCode, &x10::array::Array__ValueSequence<FMGL(T)>::iterator, &x10::array::Array__ValueSequence<FMGL(T)>::toString, &x10::array::Array__ValueSequence<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::array::Array__ValueSequence<FMGL(T)>::_itables[5] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Sequence<FMGL(T)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10_int, FMGL(T)> >, &_itable_2), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterable<FMGL(T)> >, &_itable_3), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::array::Array__ValueSequence<FMGL(T)> >())};
template<class FMGL(T)> void x10::array::Array__ValueSequence<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::array::Array__ValueSequence<FMGL(T)>");
    
}


//#line 56 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10FieldDecl_c

//#line 352 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10FieldDecl_c

//#line 353 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::Iterator<FMGL(T)> > x10::array::Array__ValueSequence<FMGL(T)>::iterator(
  ) {
    
    //#line 353 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return x10::array::Array__ValueIterator<FMGL(T)>::_make(((x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >)this)->
                                                              FMGL(out__));
    
}

//#line 354 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::array::Array__ValueSequence<FMGL(T)>::_constructor(
                          x10aux::ref<x10::array::Array<FMGL(T)> > out__)
{
    
    //#line 56 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >)this)->
      FMGL(out__) =
      out__;
    this->::x10::lang::Object::_constructor();
    {
        
        //#line 355 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.AssignPropertyCall_c
        FMGL(size) = x10aux::nullCheck(((x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >)this)->
                                         FMGL(out__))->
                       FMGL(size);
        
        //#line 351 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >)this)->x10::array::Array__ValueSequence<FMGL(T)>::__fieldInitializers1178();
    }
    
}
template<class FMGL(T)>
x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> > x10::array::Array__ValueSequence<FMGL(T)>::_make(
  x10aux::ref<x10::array::Array<FMGL(T)> > out__)
{
    x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::array::Array__ValueSequence<FMGL(T)> >(), 0, sizeof(x10::array::Array__ValueSequence<FMGL(T)>))) x10::array::Array__ValueSequence<FMGL(T)>();
    this_->_constructor(out__);
    return this_;
}



//#line 357 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::array::Array__ValueSequence<FMGL(T)>::__apply(
  x10_int i) {
    
    //#line 357 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(((x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >)this)->
                               FMGL(array))->x10::array::Array<FMGL(T)>::__apply(
             i);
    
}

//#line 351 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::array::Array__ValueSequence<FMGL(T)>::size(
  ) {
    
    //#line 351 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >)this)->
             FMGL(size);
    
}

//#line 351 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >
  x10::array::Array__ValueSequence<FMGL(T)>::x10__array__Array__ValueSequence____x10__array__Array__ValueSequence__this(
  ) {
    
    //#line 351 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >)this);
    
}

//#line 351 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::array::Array<FMGL(T)> >
  x10::array::Array__ValueSequence<FMGL(T)>::x10__array__Array__ValueSequence____x10__array__Array__this(
  ) {
    
    //#line 351 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >)this)->
             FMGL(out__);
    
}

//#line 351 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::array::Array__ValueSequence<FMGL(T)>::__fieldInitializers1178(
  ) {
    
    //#line 351 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >)this)->
      FMGL(array) = (__extension__ ({
        x10aux::ref<x10::array::Array<FMGL(T)> > __desugarer__var__1__ =
          ((x10aux::ref<x10::array::Array__ValueSequence<FMGL(T)> >)this)->
            FMGL(out__);
        
        //#line 352 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::array::Array<FMGL(T)> > __var46__;
        
        //#line 352 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Labeled_c
        goto __ret2240; __ret2240: 
        //#line 352 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10Do_c
        do
        {
        {
            
            //#line 352 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(__desugarer__var__1__,
                                        X10_NULL)) &&
                !((x10aux::struct_equals(x10aux::nullCheck(__desugarer__var__1__)->
                                           FMGL(rank),
                                         ((x10_int)1)))))
            {
                
                //#line 352 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Throw_c
                x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.array.Array[T]{self.rank==1}"))));
            }
            
            //#line 352 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Eval_c
            __var46__ =
              __desugarer__var__1__;
            
            //#line 352 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/Array.x10": polyglot.ast.Branch_c
            goto __ret2240_end_;
        }
        goto __ret2240_next_; __ret2240_next_: ;
        }
        while (false);
        goto __ret2240_end_; __ret2240_end_: ;
        __var46__;
    }))
    ;
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::array::Array__ValueSequence<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::array::Array__ValueSequence<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::array::Array__ValueSequence<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(array));
    buf.write(this->FMGL(size));
    buf.write(this->FMGL(out__));
    
}

template<class FMGL(T)> void x10::array::Array__ValueSequence<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(array) = buf.read<x10aux::ref<x10::array::Array<FMGL(T)> > >();
    FMGL(size) = buf.read<x10_int>();
    FMGL(out__) = buf.read<x10aux::ref<x10::array::Array<FMGL(T)> > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::array::Array__ValueSequence<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::array::Array__ValueSequence<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::array::Array__ValueSequence<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Sequence<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.array.Array.ValueSequence";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
#endif // X10_ARRAY_ARRAY__VALUESEQUENCE_H_IMPLEMENTATION
#endif // __X10_ARRAY_ARRAY__VALUESEQUENCE_H_NODEPS
